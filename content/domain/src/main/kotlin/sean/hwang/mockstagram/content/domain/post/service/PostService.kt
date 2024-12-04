package sean.hwang.mockstagram.content.domain.post.service

import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Service
import sean.hwang.mockstagram.content.domain.post.document.Post
import sean.hwang.mockstagram.content.domain.post.dto.PostFetchOptions
import sean.hwang.mockstagram.content.domain.post.repository.PostRepository

@Service
class PostService(
    private val postRepository: PostRepository,
    private val mongoTemplate: ReactiveMongoTemplate,
) {
    suspend fun createPost(post: Post): Post =
        postRepository.save(post).awaitFirst()

    suspend fun listPosts(
        pageable: Pageable,
        option: PostFetchOptions,
    ): FetchResult<Post> {
        val query = Query().with(getSort(option.order))

        option.filter.authorId?.let {
            query.addCriteria(Criteria.where("authorId").isEqualTo(it))
        }

        val totalCount = mongoTemplate.count(
            query,
            Post::class.java,
        ).awaitSingle()

        val elements = mongoTemplate.find(
            query.apply { // hasNext 계산을 위해 하나 더 조회한다
                skip(pageable.offset)
                limit(pageable.pageSize + 1)
            },
            Post::class.java,
        ).collectList().awaitSingle()

        return FetchResult(
            elements = elements,
            totalCount = totalCount,
        )
    }

    private fun getSort(order: PostFetchOptions.Order): Sort {
        return when (order) {
            PostFetchOptions.Order.NEWEST -> Sort.by(Sort.Direction.DESC, "createdAt")
            else -> Sort.by(Sort.Direction.DESC, "createdAt")
        }
    }

    data class FetchResult<T>(
        val elements: List<T>,
        val totalCount: Long,
    )
}
