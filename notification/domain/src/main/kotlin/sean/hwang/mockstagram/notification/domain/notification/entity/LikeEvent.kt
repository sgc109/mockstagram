package sean.hwang.mockstagram.notification.domain.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(
    name = "like_event",
    indexes = [
        Index(
            name = "uk_like_event_target_id_01",
            columnList = "targetId,targetType,likerId",
            unique = true,
        ),
    ],
)
class LikeEvent(
    @Id
    @Column(name = "entity_id")
    val entityId: Long,
    @Column(name = "notification_id", nullable = false)
    val notificationId: Long,
    @Column(name = "target_id", nullable = false)
    val targetId: String,
    @Column(name = "target_type", nullable = false)
    val targetType: String,
    @Column(name = "liker_id", nullable = false)
    val likerId: Long,
    @Column(name = "created_at", nullable = false)
    val createdAt: Instant,
    @Column(name = "updated_at", nullable = false)
    val updatedAt: Instant,
) {

}
