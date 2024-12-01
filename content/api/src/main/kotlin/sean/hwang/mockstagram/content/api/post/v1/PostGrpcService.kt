package sean.hwang.mockstagram.content.api.post.v1

import com.google.protobuf.BoolValue
import com.google.protobuf.Int32Value
import com.google.protobuf.Int64Value
import com.google.protobuf.StringValue
import com.google.protobuf.Timestamp
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.content.post.api.v1.CreatePostRequest
import sean.hwang.mockstagram.content.post.api.v1.Post
import sean.hwang.mockstagram.content.post.api.v1.PostServiceGrpcKt
import sean.hwang.mockstagram.content.post.api.v1.PostType
import sean.hwang.mockstagram.content.post.api.v1.post
import sean.hwang.mockstagram.content.post.api.v1.postPage

@Component
class PostGrpcService : PostServiceGrpcKt.PostServiceCoroutineImplBase() {
    override suspend fun createPost(request: CreatePostRequest): Post =
        post {
            this.id = "1"
            this.authorId = StringValue.of("1")
            this.likeCount = Int64Value.of(0)
            this.saveCount = Int64Value.of(0)
            this.commentCount = Int64Value.of(0)
            this.shareCount = Int64Value.of(0)
            this.viewCount = Int64Value.of(0)
            this.description = StringValue.of("This is description")
            this.pages +=
                listOf(
                    postPage {
                        this.position = 0
                        this.imageUrl = StringValue.of("https://placehold.co/400x400/000000/FFFFFF")
                        this.createdAt = Timestamp.getDefaultInstance()
                        this.updatedAt = Timestamp.getDefaultInstance()
                    },
                )
            this.type = PostType.POST_TYPE_IMAGE
            this.width = Int32Value.of(400)
            this.height = Int32Value.of(400)
            this.onHide = BoolValue.of(false)
            this.removed = BoolValue.of(false)
            this.visibleOnFeed = BoolValue.of(true)
        }
}
