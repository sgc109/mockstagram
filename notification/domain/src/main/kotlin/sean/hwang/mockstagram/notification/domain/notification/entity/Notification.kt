package sean.hwang.mockstagram.notification.domain.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import sean.hwang.mockstagram.notification.domain.notification.util.ListToCsvAttributeConverter
import java.time.Instant

@Entity
@Table(
    name = "notification",
    indexes = [
        Index(
            name = "idx_notification_receiver_id_01",
            columnList = "receiver_id,last_raised_at",
        ),
    ],
)
class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "type", nullable = false)
    val type: NotificationType,
    @Column(name = "receiver_id", nullable = false)
    val receiverId: Long,
    @Column(name = "last_raiser_id", nullable = false)
    val lastRaiserId: Long,
    @Convert(converter = ListToCsvAttributeConverter::class)
    @Column(name = "last_raiser_ids")
    val lastRaiserIds: List<Long>,
    @Column(name = "target_id")
    val targetId: Long?,
    @Column(name = "target_type")
    val targetType: NotificationTargetType?,
    @Column(name = "link_url")
    val linkUrl: String?,
    @Column(name = "thumbnail_url")
    val thumbnailUrl: String?,
    @Column(name = "last_raised_at", nullable = false)
    val lastRaisedAt: Instant = Instant.now(),
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, updatable = false)
    val updatedAt: Instant = Instant.now(),
) {
}
