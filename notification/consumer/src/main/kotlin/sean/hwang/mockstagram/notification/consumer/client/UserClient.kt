package sean.hwang.mockstagram.notification.consumer.client

import sean.hwang.mockstagram.notification.consumer.dto.UserDto

interface UserClient {
    fun fetchUserById(id: Long): UserDto?
}
