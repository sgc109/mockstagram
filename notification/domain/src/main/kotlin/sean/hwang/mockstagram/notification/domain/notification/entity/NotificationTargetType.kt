package sean.hwang.mockstagram.notification.domain.notification.entity

import sean.hwang.mockstagram.notification.domain.notification.util.StringEnumerable

enum class NotificationTargetType(
    override val dbValue: String,
): StringEnumerable<NotificationTargetType> {
    POST("post"),
    COMMENT("comment"),
    USER("user"),
}
