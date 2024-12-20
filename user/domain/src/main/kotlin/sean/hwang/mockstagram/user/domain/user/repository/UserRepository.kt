package sean.hwang.mockstagram.user.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import sean.hwang.mockstagram.user.domain.user.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}
