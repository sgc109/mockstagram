package sean.hwang.mockstagram.reaction.domain.like.event

import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType

data class LikeEvent(
    val type: Type,
    val likeId: Long,
    val likerId: Long,
    val targetId: String,
    val targetType: LikeTargetType,
    val count: Long,
) {
    enum class Type {
        CREATED,
        DELETED,
    }
}
