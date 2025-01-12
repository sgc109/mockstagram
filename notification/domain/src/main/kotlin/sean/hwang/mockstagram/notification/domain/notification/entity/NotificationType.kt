package sean.hwang.mockstagram.notification.domain.notification.entity

import sean.hwang.mockstagram.notification.domain.notification.util.Enumerable

enum class NotificationType(
    override val dbValue: Int,
) : Enumerable<NotificationType> {
    LIKE_ON_POST(0),
    LIKE_ON_COMMENT(1),
    COMMENT_ON_POST(2),
    FOLLOW(3),
}
