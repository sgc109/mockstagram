package sean.hwang.mockstagram.reaction.domain.like.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sean.hwang.mockstagram.reaction.domain.like.entity.Like
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType
import sean.hwang.mockstagram.reaction.domain.like.repository.LikeRepository

@Service
class LikeService(
    private val likeRepository: LikeRepository,
) {
    @Transactional
    fun createLike(like: Like): Like =
        likeRepository.save(like)

    @Transactional
    fun deleteLike(targetId: String, targetType: LikeTargetType, likerId: Long) {
        likeRepository.deleteByTargetIdAndTargetTypeAndLikerId(
            targetId = targetId,
            targetType = targetType,
            likerId = likerId,
        )
    }
}
