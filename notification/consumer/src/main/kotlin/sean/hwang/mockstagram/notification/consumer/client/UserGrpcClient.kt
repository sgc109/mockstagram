package sean.hwang.mockstagram.notification.consumer.client

import sean.hwang.mockstagram.notification.consumer.dto.UserDto

class UserGrpcClient(
    // TODO: Inject gRPC client stub
) : UserClient {
    override fun fetchUserById(id: Long): UserDto? {
        return null // TODO: Implement fetching user by id
    }
}
