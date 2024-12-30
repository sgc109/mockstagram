package sean.hwang.mockstagram.comment.domain.comment.service

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sean.hwang.mockstagram.comment.domain.comment.dto.CommentTarget
import sean.hwang.mockstagram.comment.domain.comment.entity.Comment
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentCounter
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType
import sean.hwang.mockstagram.comment.domain.comment.event.CommentEvent
import sean.hwang.mockstagram.comment.domain.comment.repository.CommentCounterRepository
import sean.hwang.mockstagram.comment.domain.comment.repository.CommentRepository

@Service
@Transactional(readOnly = true)
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentCounterRepository: CommentCounterRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    @Transactional
    fun createComment(comment: Comment): Comment {
        val commentCounter = commentCounterRepository.findByTargetIdAndTargetType(comment.targetId, comment.targetType)
            ?: CommentCounter(targetId = comment.targetId, targetType = comment.targetType, count = 0)

        commentCounterRepository.save(commentCounter.increment())
        return commentRepository.save(comment).also {
            publishLikeEvent(aggregate = it, counter = commentCounter, type = CommentEvent.Type.CREATED)
        }
    }

    @Transactional
    fun deleteComment(targetId: String, targetType: CommentTargetType, commenterId: Long) {
        val comment = commentRepository.findByTargetIdAndTargetTypeAndCommenterId(targetId, targetType, commenterId)

        requireNotNull(comment) {
            "comment not found for targetId=$targetId, targetType=$targetType, commenterId=$commenterId"
        }

        val commentCounter = commentCounterRepository.findByTargetIdAndTargetType(targetId, targetType)

        requireNotNull(commentCounter) {
            "commentCounter should not be null for targetId=$targetId, targetType=$targetType"
        }

        commentCounterRepository.save(commentCounter.decrement())

        comment.also {
            commentRepository.delete(it)
        }.also {
            publishLikeEvent(aggregate = it, counter = commentCounter, type = CommentEvent.Type.DELETED)
        }
    }

    fun batchGetCommentCounts(commentTargets: List<CommentTarget>): Map<CommentTarget, Long> {
        val postCommentCounts = filterCommentTargets(commentTargets, CommentTargetType.POST)
            .let {
                commentCounterRepository.findAllByTargetIdInAndTargetType(
                    targetIds = it,
                    targetType = CommentTargetType.POST,
                )
            }

        return postCommentCounts.associateBy(
            { CommentTarget(it.targetId, CommentTargetType.POST) },
            { it.count },
        )
    }

    fun batchGetComments(commentTargets: List<CommentTarget>, commenterId: Long): Set<CommentTarget> {
        val postComments = filterCommentTargets(commentTargets, CommentTargetType.POST).let {
            commentRepository.findAllByTargetIdInAndTargetTypeAndCommenterId(it, CommentTargetType.POST, commenterId)
        }
        return postComments.map { CommentTarget(it.targetId, CommentTargetType.POST) }.toSet()
    }

    private fun filterCommentTargets(commentTargets: List<CommentTarget>, targetType: CommentTargetType): List<String> {
        return commentTargets.filter { it.targetType == targetType }.map { it.targetId }
    }

    private fun publishLikeEvent(aggregate: Comment, counter: CommentCounter, type: CommentEvent.Type) {
        applicationEventPublisher.publishEvent(
            CommentEvent(
                type = type,
                commentId = aggregate.id,
                commenterId = aggregate.commenterId,
                targetId = aggregate.targetId,
                targetType = aggregate.targetType,
                count = counter.count,
            ),
        )
    }
}
