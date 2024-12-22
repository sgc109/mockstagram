package sean.hwang.mockstagram.reaction.domain.like.dto

import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType

data class LikeTarget(
    val targetId: String,
    val targetType: LikeTargetType,
)
