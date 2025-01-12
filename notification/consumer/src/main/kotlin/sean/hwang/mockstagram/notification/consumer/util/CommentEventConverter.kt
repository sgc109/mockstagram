package sean.hwang.mockstagram.notification.consumer.util

import sean.hwang.mockstagram.comment.event.comment.v1.CommentEvent
import sean.hwang.mockstagram.notification.domain.notification.entity.Notification
import sean.hwang.mockstagram.notification.domain.notification.entity.NotificationTargetType
import sean.hwang.mockstagram.notification.domain.notification.entity.NotificationType
import sean.hwang.mockstagram.reaction.api.util.toLong

object CommentEventConverter {
    fun CommentEvent.toDomain(notificationId: Long) =
        sean.hwang.mockstagram.notification.domain.notification.entity.CommentEvent(
            entityId = this.commentId.toLong(),
            notificationId = notificationId,
            targetId = this.targetId,
            targetType = this.targetType.toDomain().name,
            commenterId = this.commenterId.toLong(),
            content = this.content,
            createdAt = this.createdAt.toInstant(),
            updatedAt = this.createdAt.toInstant(),
        )

    fun CommentEvent.toNotification() = Notification(
        type = when (this.targetType) {
            CommentEvent.TargetType.COMMENT_TARGET_TYPE_POST,
            CommentEvent.TargetType.COMMENT_TARGET_TYPE_COMMENT,
                -> NotificationType.COMMENT_ON_POST

            else -> throw IllegalArgumentException("Unsupported target type: ${this.targetType}")
        },
        receiverId = 1, // TODO: Set appropriate receiverId with the post owner
        lastRaiserId = this.commenterId.toLong(),
        lastRaiserIds = listOf(this.commenterId.toLong()),
        targetId = this.targetId.toLong(),
        targetType = NotificationTargetType.POST,
        linkUrl = null, // TODO: Link appropriate deeplink URL to the post
        thumbnailUrl = null, // TODO: Set appropriate thumbnail URL of the post
    )

    fun CommentEvent.TargetType.toDomain() =
        when (this) {
            CommentEvent.TargetType.COMMENT_TARGET_TYPE_POST -> NotificationTargetType.POST
            CommentEvent.TargetType.COMMENT_TARGET_TYPE_COMMENT -> NotificationTargetType.COMMENT
            else -> throw IllegalArgumentException("Unsupported target type: $this")
        }
}
