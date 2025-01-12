package sean.hwang.mockstagram.notification.domain.notification.util

interface StringEnumerable<E : Enum<E>> {
    val dbValue: String
}
