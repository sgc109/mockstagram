package sean.hwang.mockstagram.notification.domain.notification.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.notification.domain.notification.entity.LikeEvent

interface LikeEventRepository : JpaRepository<LikeEvent, Long> {
    fun findOneByTargetIdAndTargetType(targetId: String, targetType: String): LikeEvent?

    fun findByEntityId(entityId: Long): LikeEvent?
}
