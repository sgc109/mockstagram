package sean.hwang.mockstagram.notification.domain.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "follow_event",
    indexes = [
        Index(
            name = "uk_follow_event_follower_id_01",
            columnList = "follower_id,followee_id",
            unique = true,
        ),
    ],
)
class FollowEvent(
    @Id
    @Column(name = "entity_id")
    val entityId: Long,
    @Column(name = "notification_id", nullable = false)
    val notificationId: Long,
    @Column(name = "follower_id", nullable = false)
    val followerId: Long,
    @Column(name = "followee_id", nullable = false)
    val followeeId: Long,
) {
}
