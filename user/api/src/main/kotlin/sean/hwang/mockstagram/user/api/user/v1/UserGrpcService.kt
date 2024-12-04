package sean.hwang.mockstagram.user.api.user.v1

import org.springframework.stereotype.Component
import sean.hwang.mockstagram.content.api.user.v1.FindUserRequest
import sean.hwang.mockstagram.content.api.user.v1.UserServiceGrpcKt
import sean.hwang.mockstagram.user.api.user.v1.converter.Converters.toProto
import sean.hwang.mockstagram.user.api.util.notNullValue
import sean.hwang.mockstagram.user.domain.user.service.UserService

@Component
class UserGrpcService(
    private val userService: UserService,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {
    override suspend fun findUser(request: FindUserRequest): User {
        return userService.getUserByUsername(request.filter.username.notNullValue()).toProto()
    }
}
