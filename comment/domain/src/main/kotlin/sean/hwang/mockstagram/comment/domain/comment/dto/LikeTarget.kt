package sean.hwang.mockstagram.comment.domain.comment.dto

import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType

data class CommentTarget(
    val targetId: String,
    val targetType: CommentTargetType,
)
