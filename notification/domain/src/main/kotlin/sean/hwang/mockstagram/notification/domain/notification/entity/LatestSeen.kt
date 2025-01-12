package sean.hwang.mockstagram.notification.domain.notification.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(
    name = "latest_seen",
    indexes = [
        Index(
            name = "uk_latest_seen_userId_01",
            columnList = "userId",
            unique = true,
        ),
    ],
)
class LatestSeen(
    @Id
    @Column(name = "user_id", nullable = false)
    val userId: Long,
    @Column(name = "last_seen_at", nullable = false)
    val lastSeenAt: Instant,
) {
}
