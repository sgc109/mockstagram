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
    name = "like_counter",
    indexes = [
        Index(
            name = "uk_like_counter_target_id_01",
            columnList = "targetId,targetType",
            unique = true,
        ),
    ],
)
@EntityListeners(AuditingEntityListener::class)
data class LikeCounter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "target_id", nullable = false)
    val targetId: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false)
    val targetType: LikeTargetType,
    @Column(name = "count", nullable = false)
    var count: Long = 0,
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, updatable = false)
    val updatedAt: Instant = Instant.now(),
) {
    fun increment(): LikeCounter {
        count++
        return this
    }

    fun decrement(): LikeCounter {
        count--
        return this
    }
}
