package sean.hwang.mockstagram.notification.consumer.dto

import java.time.ZonedDateTime

data class LikeEvent(
    val id: Long,
    val likerId: Long,
    val targetId: String,
    val targetType: LikeTargetType,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
)

enum class LikeTargetType {
    POST,
    COMMENT,
}
