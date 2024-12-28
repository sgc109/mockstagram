package sean.hwang.mockstagram.reaction.domain.like.service

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sean.hwang.mockstagram.reaction.domain.like.dto.LikeTarget
import sean.hwang.mockstagram.reaction.domain.like.entity.Like
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeCounter
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeOutbox
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType
import sean.hwang.mockstagram.reaction.domain.like.repository.LikeCounterRepository
import sean.hwang.mockstagram.reaction.domain.like.repository.LikeRepository

@Service
@Transactional(readOnly = true)
class LikeService(
    private val likeRepository: LikeRepository,
    private val likeCounterRepository: LikeCounterRepository,
    private val entityManager: EntityManager,
) {
    @Transactional
    fun createLike(like: Like): Like {
        val likeCounter = likeCounterRepository.findByTargetIdAndTargetType(like.targetId, like.targetType)
            ?: LikeCounter(targetId = like.targetId, targetType = like.targetType, count = 0)

        likeCounterRepository.save(likeCounter.increment())
        return likeRepository.save(like).also {
            publishLikeEvent(aggregate = it, type = LikeOutbox.EventType.CREATED)
        }
    }

    @Transactional
    fun deleteLike(targetId: String, targetType: LikeTargetType, likerId: Long) {
        val like = likeRepository.findByTargetIdAndTargetTypeAndLikerId(targetId, targetType, likerId)

        requireNotNull(like) {
            "like not found for targetId=$targetId, targetType=$targetType, likerId=$likerId"
        }

        val likeCounter = likeCounterRepository.findByTargetIdAndTargetType(targetId, targetType)

        requireNotNull(likeCounter) {
            "likeCounter should not be null for targetId=$targetId, targetType=$targetType"
        }

        likeCounterRepository.save(likeCounter.decrement())

        like.also {
            likeRepository.delete(it)
        }.also {
            publishLikeEvent(aggregate = it, type = LikeOutbox.EventType.DELETED)
        }
    }

    fun batchGetLikeCounts(likeTargets: List<LikeTarget>): Map<LikeTarget, Long> {
        val postLikeCounts = filterLikeTargets(likeTargets, LikeTargetType.POST)
            .let {
                likeCounterRepository.findAllByTargetIdInAndTargetType(targetIds = it, targetType = LikeTargetType.POST)
            }

        return postLikeCounts.associateBy(
            { LikeTarget(it.targetId, LikeTargetType.POST) },
            { it.count }
        )
    }

    fun batchGetLikes(likeTargets: List<LikeTarget>, likerId: Long): Set<LikeTarget> {
        val postLikes = filterLikeTargets(likeTargets, LikeTargetType.POST).let {
            likeRepository.findAllByTargetIdInAndTargetTypeAndLikerId(it, LikeTargetType.POST, likerId)
        }
        return postLikes.map { LikeTarget(it.targetId, LikeTargetType.POST) }.toSet()
    }

    private fun filterLikeTargets(likeTargets: List<LikeTarget>, targetType: LikeTargetType): List<String> {
        return likeTargets.filter { it.targetType == targetType }.map { it.targetId }
    }

    private fun publishLikeEvent(aggregate: Like, type: LikeOutbox.EventType) {
        entityManager.persist(
            LikeOutbox(
                aggregateId = aggregate.id,
                type = type,
                payload = aggregate,
            )
        )
    }
}
