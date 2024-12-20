package sean.hwang.mockstagram.user.domain.user.service

import org.springframework.stereotype.Service
import sean.hwang.mockstagram.user.domain.user.entity.User
import sean.hwang.mockstagram.user.domain.user.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUserByUsername(username: String): User =
        userRepository.findByUsername(username) ?: throw NoSuchElementException("User not found(username=$username)")
}
