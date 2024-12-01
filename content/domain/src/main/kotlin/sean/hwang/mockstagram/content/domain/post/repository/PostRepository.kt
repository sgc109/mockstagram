package sean.hwang.mockstagram.content.domain.post.repository

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import sean.hwang.mockstagram.content.domain.post.document.Post

interface PostRepository : ReactiveMongoRepository<Post, ObjectId>
