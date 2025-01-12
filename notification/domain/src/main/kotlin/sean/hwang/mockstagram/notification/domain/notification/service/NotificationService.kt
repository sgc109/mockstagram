package sean.hwang.mockstagram.notification.domain.notification.service

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sean.hwang.mockstagram.notification.domain.notification.entity.LatestSeen
import sean.hwang.mockstagram.notification.domain.notification.entity.Notification
import sean.hwang.mockstagram.notification.domain.notification.repository.LatestSeenRepository
import sean.hwang.mockstagram.notification.domain.notification.repository.NotificationRepository
import java.time.Instant

@Service
@Transactional(readOnly = true)
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val latestSeenRepository: LatestSeenRepository,
) {
    fun listNotifications(
        receiverId: Long,
        lastSeenAt: Instant,
        pageable: Pageable
    ): List<Notification> {
        return emptyList()
    }

    @Transactional
    fun createNotification(receiverId: Long) {
    }

    @Transactional
    fun markAsSeen(userId: Long) {
        val latestSeenToUpdate = latestSeenRepository.findByUserId(
            userId = userId
        ) ?: LatestSeen(
            userId = userId,
            lastSeenAt = Instant.now()
        )

        latestSeenRepository.save(latestSeenToUpdate)
    }
}
