package sean.hwang.mockstagram.notification.domain.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(
    name = "comment_event",
    indexes = [
        Index(
            name = "uk_comment_event_entity_id_01",
            columnList = "entity_id",
            unique = true,
        ),
    ],
)
class CommentEvent(
    @Id
    @Column(name = "entity_id")
    val entityId: Long,
    @Column(name = "notification_id", nullable = false)
    val notificationId: Long,
    @Column(name = "target_id", nullable = false)
    val targetId: String,
    @Column(name = "target_type", nullable = false)
    val targetType: String,
    @Column(name = "author_id", nullable = false)
    val commenterId: Long,
    @Column(name = "content", nullable = false)
    val content: String,
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,
    @Column(name = "updated_at", nullable = false)
    val updatedAt: Instant,
) {
}
