package sean.hwang.mockstagram.reaction.api.like.v1.converter

import sean.hwang.mockstagram.reaction.api.like.v1.Like
import sean.hwang.mockstagram.reaction.api.like.v1.Like.TargetType
import sean.hwang.mockstagram.reaction.api.like.v1.like
import sean.hwang.mockstagram.reaction.api.post.v1.CreateLikeRequest
import sean.hwang.mockstagram.reaction.api.post.v1.LikeTarget
import sean.hwang.mockstagram.reaction.api.post.v1.likeTarget
import sean.hwang.mockstagram.reaction.api.util.notNullValue
import sean.hwang.mockstagram.reaction.api.util.toLong
import sean.hwang.mockstagram.reaction.api.util.toStringValue
import sean.hwang.mockstagram.reaction.api.util.toTimestamp

object Converters {
    fun sean.hwang.mockstagram.reaction.domain.like.entity.Like.toProto(): Like {
        val entity = this
        return like {
            this.id = entity.id.toString()
            this.targetId = entity.targetId.toStringValue()
            this.targetType = entity.targetType.toProto()
            this.likerId = entity.likerId.toStringValue()
            this.createdAt = entity.createdAt.toTimestamp()
            this.updatedAt = entity.updatedAt.toTimestamp()
        }
    }

    fun CreateLikeRequest.toEntity(): sean.hwang.mockstagram.reaction.domain.like.entity.Like {
        return sean.hwang.mockstagram.reaction.domain.like.entity.Like(
            targetId = this.targetId.notNullValue(),
            targetType = this.targetType.toDomain(),
            likerId = this.requesterId.toLong(),
        )
    }

    fun TargetType.toDomain() =
        when (this) {
            TargetType.LIKE_TARGET_TYPE_POST -> sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType.POST
            TargetType.LIKE_TARGET_TYPE_UNSPECIFIED, TargetType.UNRECOGNIZED ->
                throw IllegalArgumentException("Invalid PostType: $this")
        }

    private fun sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType.toProto() =
        when (this) {
            sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType.POST -> TargetType.LIKE_TARGET_TYPE_POST
        }

    fun LikeTarget.toDomain() =
        sean.hwang.mockstagram.reaction.domain.like.dto.LikeTarget(
            targetId = this.id.notNullValue(),
            targetType = this.type.toDomain(),
        )

    fun sean.hwang.mockstagram.reaction.domain.like.dto.LikeTarget.toProto(): LikeTarget {
        val dto = this

        return likeTarget {
            this.id = dto.targetId.toStringValue()
            this.type = dto.targetType.toProto()
        }
    }
}
