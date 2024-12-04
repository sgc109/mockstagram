package sean.hwang.mockstagram.content.api.post.v1

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.content.api.common.Page
import sean.hwang.mockstagram.content.api.post.v1.ListPostsRequest.Order
import sean.hwang.mockstagram.content.api.post.v1.converter.Converters.toDocument
import sean.hwang.mockstagram.content.api.post.v1.converter.Converters.toProto
import sean.hwang.mockstagram.content.api.util.orElseNull
import sean.hwang.mockstagram.content.domain.post.dto.PostFetchOptions
import sean.hwang.mockstagram.content.domain.post.service.PostService

@Component
class PostGrpcService(
    private val postService: PostService,
) : PostServiceGrpcKt.PostServiceCoroutineImplBase() {
    override suspend fun createPost(request: CreatePostRequest): Post {
        return postService.createPost(request.toDocument()).toProto()
    }

    override suspend fun listPosts(request: ListPostsRequest): ListPostsResponse {
        val result = postService.listPosts(
            pageable = request.page.toPageable(),
            option = request.toOption(),
        )

        return listPostsResponse {
            this.posts += result.elements.map { it.toProto() }
        }
    }

    private fun Page.toPageable(defaultSize: Int = 20): Pageable {
        val page = num.takeIf { it > 0 } ?: 1
        val size = size.takeIf { it > 0 } ?: defaultSize
        return PageRequest.of(page - 1, size)
    }

    private fun ListPostsRequest.toOption(): PostFetchOptions {
        return PostFetchOptions(
            order = order.toDomain(),
            filter = PostFetchOptions.Filter(
                authorId = filter.authorId.orElseNull(),
            ),
        )
    }

    private fun Order.toDomain(): PostFetchOptions.Order {
        return when (this) {
            Order.ORDER_NEWEST -> PostFetchOptions.Order.NEWEST
            Order.ORDER_UNSPECIFIED -> PostFetchOptions.Order.NEWEST
            else -> throw IllegalArgumentException("Unknown order: $this")
        }
    }
}
