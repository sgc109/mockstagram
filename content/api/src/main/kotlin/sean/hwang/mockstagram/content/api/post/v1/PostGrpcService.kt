package sean.hwang.mockstagram.content.api.post.v1

import org.springframework.stereotype.Component
import sean.hwang.mockstagram.content.api.post.v1.converter.Converters.toDocument
import sean.hwang.mockstagram.content.api.post.v1.converter.Converters.toProto
import sean.hwang.mockstagram.content.domain.post.service.PostService

@Component
class PostGrpcService(
    private val postService: PostService,
) : PostServiceGrpcKt.PostServiceCoroutineImplBase() {
    override suspend fun createPost(request: CreatePostRequest): Post {
        return postService.createPost(request.toDocument()).toProto()
    }
}
