package sean.hwang.mockstagram.comment.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.comment.domain.comment.entity.Comment
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTargetIdAndTargetTypeAndCommenterId(
        targetId: String,
        targetType: CommentTargetType,
        commenterId: Long,
    ): Comment?

    fun findAllByTargetIdInAndTargetTypeAndCommenterId(
        targetIds: List<String>,
        targetType: CommentTargetType,
        commenterId: Long,
    ): List<Comment>
}
