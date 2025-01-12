package sean.hwang.mockstagram.notification.domain.notification.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.notification.domain.notification.entity.CommentEvent

interface CommentEventRepository : JpaRepository<CommentEvent, Long> {
    fun findByEntityId(entityId: Long): CommentEvent?
}
