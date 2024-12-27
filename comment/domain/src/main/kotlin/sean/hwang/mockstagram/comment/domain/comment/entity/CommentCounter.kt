package sean.hwang.mockstagram.comment.domain.comment.entity

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
    name = "comment_counter",
    indexes = [
        Index(
            name = "uk_comment_counter_target_id_01",
            columnList = "targetId,targetType",
            unique = true,
        ),
    ],
)
@EntityListeners(AuditingEntityListener::class)
data class CommentCounter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "target_id", nullable = false)
    val targetId: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false)
    val targetType: CommentTargetType,
    @Column(name = "total_count", nullable = false) // NOTE: This is the total count of comments including replies
    var totalCount: Long = 0,
    @Column(name = "count", nullable = false)
    var count: Long = 0,
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, updatable = false)
    val updatedAt: Instant = Instant.now(),
) {
    fun increment(): CommentCounter {
        count++
        return this
    }

    fun decrement(): CommentCounter {
        count--
        return this
    }
}
