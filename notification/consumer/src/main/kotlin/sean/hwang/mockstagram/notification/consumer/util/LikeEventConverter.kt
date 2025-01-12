package sean.hwang.mockstagram.notification.consumer.util

import sean.hwang.mockstagram.notification.domain.notification.entity.Notification
import sean.hwang.mockstagram.notification.domain.notification.entity.NotificationTargetType
import sean.hwang.mockstagram.notification.domain.notification.entity.NotificationType
import sean.hwang.mockstagram.reaction.api.util.toLong
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent

object LikeEventConverter {
    fun LikeEvent.toDomain(notificationId: Long) =
        sean.hwang.mockstagram.notification.domain.notification.entity.LikeEvent(
            entityId = this.likeId.toLong(),
            notificationId = notificationId,
            targetId = this.targetId,
            targetType = this.targetType.toDomain().name,
            likerId = this.likerId.toLong(),
            createdAt = this.createdAt.toInstant(),
            updatedAt = this.createdAt.toInstant(),
        )

    fun LikeEvent.toNotification() = Notification(
        type = when (this.targetType) {
            LikeEvent.TargetType.LIKE_TARGET_TYPE_POST,
                -> NotificationType.LIKE_ON_POST

            else -> throw IllegalArgumentException("Unsupported target type: ${this.targetType}")
        },
        receiverId = 1, // TODO: Set appropriate receiverId with the post owner
        lastRaiserId = this.likerId.toLong(),
        lastRaiserIds = listOf(this.likeId.toLong()),
        targetId = this.targetId.toLong(),
        targetType = NotificationTargetType.POST,
        linkUrl = null, // TODO: Link appropriate deeplink URL to the post
        thumbnailUrl = null, // TODO: Set appropriate thumbnail URL of the post
    )

    fun LikeEvent.TargetType.toDomain() =
        when (this) {
            LikeEvent.TargetType.LIKE_TARGET_TYPE_POST -> NotificationTargetType.POST
            else -> throw IllegalArgumentException("Unsupported target type: $this")
        }
}
