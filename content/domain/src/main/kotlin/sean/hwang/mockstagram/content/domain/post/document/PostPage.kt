package sean.hwang.mockstagram.content.domain.post.document

import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant

data class PostPage(
    @Field("position")
    val position: Int,
    @Field("image_url")
    val imageUrl: String? = null,
    @Field("video_url")
    val videoUrl: String? = null,
    @Field("created_at")
    val createdAt: Instant? = null,
    @Field("updated_at")
    val updatedAt: Instant? = null,
)
