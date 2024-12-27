package sean.hwang.mockstagram.comment.domain.comment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sean.hwang.mockstagram.comment.domain.comment.dto.CommentTarget
import sean.hwang.mockstagram.comment.domain.comment.entity.Comment
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentCounter
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType
import sean.hwang.mockstagram.comment.domain.comment.repository.CommentCounterRepository
import sean.hwang.mockstagram.comment.domain.comment.repository.CommentRepository

@Service
@Transactional(readOnly = true)
class CommentService(
    private val commentRepository: CommentRepository,
    private val commentCounterRepository: CommentCounterRepository,
) {
    @Transactional
    fun createComment(comment: Comment): Comment {
        val commentCounter = commentCounterRepository.findByTargetIdAndTargetType(comment.targetId, comment.targetType)
            ?: CommentCounter(targetId = comment.targetId, targetType = comment.targetType, count = 0)

        commentCounterRepository.save(commentCounter.increment())
        return commentRepository.save(comment)
    }

    @Transactional
    fun deleteComment(targetId: String, targetType: CommentTargetType, authorId: Long) {
        val comment = commentRepository.findByTargetIdAndTargetTypeAndAuthorId(targetId, targetType, authorId)

        requireNotNull(comment) {
            "comment not found for targetId=$targetId, targetType=$targetType, authorId=$authorId"
        }

        val commentCounter = commentCounterRepository.findByTargetIdAndTargetType(targetId, targetType)

        requireNotNull(commentCounter) {
            "commentCounter should not be null for targetId=$targetId, targetType=$targetType"
        }

        commentCounterRepository.save(commentCounter.decrement())

        commentRepository.delete(comment)
    }

    fun batchGetCommentCounts(commentTargets: List<CommentTarget>): Map<CommentTarget, Long> {
        val postCommentCounts = filterCommentTargets(commentTargets, CommentTargetType.POST)
            .let {
                commentCounterRepository.findAllByTargetIdInAndTargetType(targetIds = it, targetType = CommentTargetType.POST)
            }

        return postCommentCounts.associateBy(
            { CommentTarget(it.targetId, CommentTargetType.POST) },
            { it.count }
        )
    }

    fun batchGetComments(commentTargets: List<CommentTarget>, authorId: Long): Set<CommentTarget> {
        val postComments = filterCommentTargets(commentTargets, CommentTargetType.POST).let {
            commentRepository.findAllByTargetIdInAndTargetTypeAndAuthorId(it, CommentTargetType.POST, authorId)
        }
        return postComments.map { CommentTarget(it.targetId, CommentTargetType.POST) }.toSet()
    }

    private fun filterCommentTargets(commentTargets: List<CommentTarget>, targetType: CommentTargetType): List<String> {
        return commentTargets.filter { it.targetType == targetType }.map { it.targetId }
    }
}
