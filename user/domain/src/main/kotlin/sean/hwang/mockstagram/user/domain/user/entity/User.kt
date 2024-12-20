package sean.hwang.mockstagram.user.domain.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Entity
@Table(
    name = "user",
    indexes = [
        Index(
            name = "uk_user_username_01",
            columnList = "username",
            unique = true,
        ),
    ]
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "username", nullable = false)
    val username: String,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "bio")
    val bio: String? = null,
    @Column(name = "image_url")
    val imageUrl: String? = null,
    @Column(name = "thumbnail_url")
    val thumbnailUrl: String? = null,
    @Column(name = "website_url")
    val websiteUrl: String? = null,
    @Column(name = "post_count", nullable = false)
    val postCount: Long = 0,
    @Column(name = "follower_count", nullable = false)
    val followerCount: Long = 0,
    @Column(name = "following_count", nullable = false)
    val followingCount: Long = 0,
    @Column(name = "is_hidden", nullable = false)
    val isHidden: Boolean = false,
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, updatable = false)
    val updatedAt: Instant = Instant.now(),
)
