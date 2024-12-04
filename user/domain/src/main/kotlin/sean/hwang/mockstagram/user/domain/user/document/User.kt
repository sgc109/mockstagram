package sean.hwang.mockstagram.user.domain.user.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant

@Document(collection = "users")
data class User(
    @Id
    val id: ObjectId? = null,
    @Field("username")
    val username: String,
    @Indexed(unique = true)
    @Field("name")
    val name: String,
    @Field("bio")
    val bio: String? = null,
    @Field("image_url")
    val imageUrl: String? = null,
    @Field("thumbnail_url")
    val thumbnailUrl: String? = null,
    @Field("website_url")
    val websiteUrl: String? = null,
    @Field("post_count")
    val postCount: Long = 0,
    @Field("follower_count")
    val followerCount: Long = 0,
    @Field("following_count")
    val followingCount: Long = 0,
    @Field("is_hidden")
    val isHidden: Boolean = false,
    @CreatedDate
    @Field("created_at")
    val createdAt: Instant? = null,
    @LastModifiedDate
    @Field("updated_at")
    val updatedAt: Instant? = null,
)
