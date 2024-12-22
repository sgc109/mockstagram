package sean.hwang.mockstagram.reaction.domain.like.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeCounter
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType

interface LikeCounterRepository : JpaRepository<LikeCounter, Long> {
    fun findByTargetIdAndTargetType(targetId: String, targetType: LikeTargetType): LikeCounter?

    fun findAllByTargetIdInAndTargetType(targetIds: List<String>, targetType: LikeTargetType): List<LikeCounter>
}
