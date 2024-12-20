package sean.hwang.mockstagram.reaction.domain.like.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.reaction.domain.like.entity.Like
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType

interface LikeRepository : JpaRepository<Like, Long> {
    fun findByTargetIdAndTargetTypeAndLikerId(targetId: String, targetType: LikeTargetType, likerId: Long): Like?
}
