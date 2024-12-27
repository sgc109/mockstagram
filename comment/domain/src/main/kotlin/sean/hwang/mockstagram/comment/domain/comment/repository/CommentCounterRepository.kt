package sean.hwang.mockstagram.comment.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentCounter
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType

interface CommentCounterRepository : JpaRepository<CommentCounter, Long> {
    fun findByTargetIdAndTargetType(targetId: String, targetType: CommentTargetType): CommentCounter?

    fun findAllByTargetIdInAndTargetType(targetIds: List<String>, targetType: CommentTargetType): List<CommentCounter>
}
