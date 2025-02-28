package sean.hwang.mockstagram.reaction.api.like.v1

import com.google.protobuf.Empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.reaction.api.like.v1.converter.Converters.toDomain
import sean.hwang.mockstagram.reaction.api.like.v1.converter.Converters.toEntity
import sean.hwang.mockstagram.reaction.api.like.v1.converter.Converters.toProto
import sean.hwang.mockstagram.reaction.api.util.notNullValue
import sean.hwang.mockstagram.reaction.api.util.toBoolValue
import sean.hwang.mockstagram.reaction.api.util.toLong
import sean.hwang.mockstagram.reaction.api.util.toLongOrNull
import sean.hwang.mockstagram.reaction.api.util.toUInt64Value
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
                likerId = request.requesterId.toLong(),
            )
        }

        return Empty.getDefaultInstance()
    }

    override suspend fun batchGetLikeStats(request: BatchGetLikeStatsRequest): BatchGetLikeStatsResponse {
        val requesterId = request.requesterId.toLongOrNull()

        val likeTargets = request.likeTargetsList.map { it.toDomain() }
        val likeCounts = withContext(Dispatchers.IO) {
            likeService.batchGetLikeCounts(likeTargets)
        }

        val likes = requesterId?.let {
            withContext(Dispatchers.IO) {
                likeService.batchGetLikes(
                    likeTargets = likeTargets,
                    likerId = it,
                )
            }
        }

        return batchGetLikeStatsResponse {
            this.likeStats += likeTargets.mapNotNull { likeTarget ->
                val likeCount = likeCounts[likeTarget]
                likeCount?.let {
                    likeStat {
                        this.target = likeTarget.toProto()
                        this.likeCount = it.toUInt64Value()
                        likes?.let {
                            this.isLiked = (likeTarget in it).toBoolValue()
                        }
                    }
                }
            }
        }
    }
}
