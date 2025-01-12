package sean.hwang.mockstagram.notification.domain.notification.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.notification.domain.notification.entity.FollowEvent

interface FollowEventRepository : JpaRepository<FollowEvent, Long> {
}
