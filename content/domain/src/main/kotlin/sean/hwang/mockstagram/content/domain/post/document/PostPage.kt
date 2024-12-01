package sean.hwang.mockstagram.content.domain.post.document

import org.springframework.data.mongodb.core.mapping.Field

data class PostPage(
    @Field("position")
    val position: Int,
    @Field("image_url")
    val imageUrl: String,
    @Field("video_url")
    val videoUrl: String? = null,
)
