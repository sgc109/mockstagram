package sean.hwang.mockstagram.content.domain.post.service

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Service
import sean.hwang.mockstagram.content.domain.post.document.Post
import sean.hwang.mockstagram.content.domain.post.repository.PostRepository

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    suspend fun createPost(post: Post): Post =
        postRepository.save(post).awaitFirst()
}
