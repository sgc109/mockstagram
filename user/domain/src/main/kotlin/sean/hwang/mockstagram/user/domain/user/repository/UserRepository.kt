package sean.hwang.mockstagram.user.domain.user.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import sean.hwang.mockstagram.user.domain.user.document.User

interface UserRepository : ReactiveMongoRepository<User, ObjectId> {
    suspend fun findByUsername(username: String): User?
}
