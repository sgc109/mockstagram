package sean.hwang.mockstagram.comment.api.comment.v1.converter

import sean.hwang.mockstagram.comment.api.comment.v1.Comment
import sean.hwang.mockstagram.comment.api.comment.v1.Comment.TargetType
import sean.hwang.mockstagram.comment.api.comment.v1.CommentTarget
import sean.hwang.mockstagram.comment.api.comment.v1.CreateCommentRequest
import sean.hwang.mockstagram.comment.api.comment.v1.comment
import sean.hwang.mockstagram.comment.api.comment.v1.commentTarget
import sean.hwang.mockstagram.comment.api.util.notNullValue
import sean.hwang.mockstagram.comment.api.util.toLong
import sean.hwang.mockstagram.comment.api.util.toStringValue
import sean.hwang.mockstagram.comment.api.util.toTimestamp

object Converters {
    fun sean.hwang.mockstagram.comment.domain.comment.entity.Comment.toProto(): Comment {
        val entity = this
        return comment {
            this.id = entity.id.toString()
            this.targetId = entity.targetId.toStringValue()
            this.targetType = entity.targetType.toProto()
            this.commenterId = entity.commenterId.toStringValue()
            this.createdAt = entity.createdAt.toTimestamp()
            this.updatedAt = entity.updatedAt.toTimestamp()
        }
    }

    fun CreateCommentRequest.toEntity(): sean.hwang.mockstagram.comment.domain.comment.entity.Comment {
        return sean.hwang.mockstagram.comment.domain.comment.entity.Comment(
            targetId = this.targetId.notNullValue(),
            targetType = this.targetType.toDomain(),
            commenterId = this.requesterId.toLong(),
            text = this.text.notNullValue(),
        )
    }

    fun TargetType.toDomain() =
        when (this) {
            TargetType.COMMENT_TARGET_TYPE_POST -> sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType.POST
            TargetType.COMMENT_TARGET_TYPE_COMMENT -> sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType.COMMENT
            TargetType.COMMENT_TARGET_TYPE_UNSPECIFIED, TargetType.UNRECOGNIZED ->
                throw IllegalArgumentException("Invalid CommentType: $this")
        }

    private fun sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType.toProto() =
        when (this) {
            sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType.POST -> TargetType.COMMENT_TARGET_TYPE_POST
            sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType.COMMENT -> TargetType.COMMENT_TARGET_TYPE_COMMENT
        }

    fun CommentTarget.toDomain() =
        sean.hwang.mockstagram.comment.domain.comment.dto.CommentTarget(
            targetId = this.id.notNullValue(),
            targetType = this.type.toDomain(),
        )

    fun sean.hwang.mockstagram.comment.domain.comment.dto.CommentTarget.toProto(): CommentTarget {
        val dto = this

        return commentTarget {
            this.id = dto.targetId.toStringValue()
            this.type = dto.targetType.toProto()
        }
    }
}
