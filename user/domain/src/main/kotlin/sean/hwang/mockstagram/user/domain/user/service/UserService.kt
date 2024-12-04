package sean.hwang.mockstagram.user.domain.user.service

import org.springframework.stereotype.Service
import sean.hwang.mockstagram.user.domain.user.document.User
import sean.hwang.mockstagram.user.domain.user.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    suspend fun getUserByUsername(username: String): User =
        userRepository.findByUsername(username) ?: throw IllegalArgumentException("User not found(username=$username)")
}
