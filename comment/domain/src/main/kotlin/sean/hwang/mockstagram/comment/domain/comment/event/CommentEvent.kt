package sean.hwang.mockstagram.comment.domain.comment.event

import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType

data class CommentEvent(
    val type: Type,
    val commentId: Long,
    val commenterId: Long,
    val targetId: String,
    val targetType: CommentTargetType,
    val count: Long,
) {
    enum class Type {
        CREATED,
        DELETED,
    }
}

