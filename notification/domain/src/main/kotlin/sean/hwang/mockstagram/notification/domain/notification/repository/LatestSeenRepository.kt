package sean.hwang.mockstagram.notification.domain.notification.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.notification.domain.notification.entity.LatestSeen

interface LatestSeenRepository : JpaRepository<LatestSeen, Long> {
    fun findByUserId(userId: Long): LatestSeen?
}
