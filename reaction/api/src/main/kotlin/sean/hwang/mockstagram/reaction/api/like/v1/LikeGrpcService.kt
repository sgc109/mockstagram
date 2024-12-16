package sean.hwang.mockstagram.reaction.api.like.v1

import com.google.protobuf.Empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.reaction.api.like.v1.converter.Converters.toDomain
import sean.hwang.mockstagram.reaction.api.like.v1.converter.Converters.toEntity
import sean.hwang.mockstagram.reaction.api.like.v1.converter.Converters.toProto
import sean.hwang.mockstagram.reaction.api.post.v1.CreateLikeRequest
import sean.hwang.mockstagram.reaction.api.post.v1.DeleteLikeRequest
import sean.hwang.mockstagram.reaction.api.post.v1.LikeServiceGrpcKt
import sean.hwang.mockstagram.reaction.api.util.notNullValue
import sean.hwang.mockstagram.reaction.domain.like.service.LikeService

@Component
class LikeGrpcService(
    private val likeService: LikeService,
) : LikeServiceGrpcKt.LikeServiceCoroutineImplBase() {
    override suspend fun createLike(request: CreateLikeRequest): Like {
        val like = request.toEntity()

        return withContext(Dispatchers.IO) {
            likeService.createLike(like)
        }.toProto()
    }

    override suspend fun deleteLike(request: DeleteLikeRequest): Empty {
        withContext(Dispatchers.IO) {
            likeService.deleteLike(
                targetId = request.targetId.notNullValue(),
                targetType = request.targetType.toDomain(),
                userId = request.requesterId.notNullValue(),
            )
        }

        return Empty.getDefaultInstance()
    }
}
