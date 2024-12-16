package sean.hwang.mockstagram.reaction.domain.like.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(
    name = "likes",
    indexes = [
        Index(
            name = "uk_likes_target_id_01",
            columnList = "targetId,targetType,userId",
            unique = true,
        ),
    ]
)
@EntityListeners(AuditingEntityListener::class)
data class Like(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "target_id", nullable = false)
    val targetId: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false)
    val targetType: LikeTargetType,
    @Column(name = "user_id", nullable = false)
    val userId: String,
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, updatable = false)
    val updatedAt: Instant = Instant.now(),
)
