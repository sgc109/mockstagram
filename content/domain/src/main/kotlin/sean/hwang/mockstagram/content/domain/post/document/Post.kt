import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import sean.hwang.mockstagram.content.domain.post.document.PostPage
import sean.hwang.mockstagram.content.domain.post.document.PostType
import java.time.Instant

@Document(collection = "posts")
data class Post(
    @Id
    val id: String,
    @Field("type")
    val type: PostType,
    @Field("author_id")
    val authorId: String? = null,
    @Field("description")
    val description: String? = null,
    @Field("width")
    val width: Int? = null,
    @Field("height")
    val height: Int? = null,
    @Field("pages")
    val pages: List<PostPage> = emptyList(),
    @Field("like_count")
    val likeCount: Long = 0,
    @Field("save_count")
    val saveCount: Long = 0,
    @Field("comment_count")
    val commentCount: Long = 0,
    @Field("share_count")
    val shareCount: Long = 0,
    @Field("view_count")
    val viewCount: Long = 0,
    @Field("on_hide")
    val onHide: Boolean = false,
    @Field("visible_on_feed")
    val visibleOnFeed: Boolean = true,
    @Field("removed")
    val removed: Boolean = false,
    @Field("created_at")
    val createdAt: Instant? = null,
    @Field("updated_at")
    val updatedAt: Instant? = null,
)
