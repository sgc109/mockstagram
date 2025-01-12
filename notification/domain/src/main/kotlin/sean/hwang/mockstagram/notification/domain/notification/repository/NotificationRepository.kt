package sean.hwang.mockstagram.notification.domain.notification.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.notification.domain.notification.entity.Notification
import java.time.Instant

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByReceiverIdGreaterThanEqualOrderByCreatedAtDesc(
        receiverId: Long,
        latestSeenAt: Instant,
        pageable: Pageable,
    ): List<Notification>
}
