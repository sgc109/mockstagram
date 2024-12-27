package sean.hwang.mockstagram.comment.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.comment.domain.comment.entity.Comment
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTargetIdAndTargetTypeAndAuthorId(targetId: String, targetType: CommentTargetType, authorId: Long): Comment?

    fun findAllByTargetIdInAndTargetTypeAndAuthorId(
        targetIds: List<String>,
        targetType: CommentTargetType,
        authorId: Long
    ): List<Comment>
}
