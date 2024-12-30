package sean.hwang.mockstagram.comment.api.comment.v1

import com.google.protobuf.Empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.comment.api.comment.v1.converter.Converters.toDomain
import sean.hwang.mockstagram.comment.api.comment.v1.converter.Converters.toEntity
import sean.hwang.mockstagram.comment.api.comment.v1.converter.Converters.toProto
import sean.hwang.mockstagram.comment.api.util.notNullValue
import sean.hwang.mockstagram.comment.api.util.toLong
import sean.hwang.mockstagram.comment.api.util.toLongOrNull
import sean.hwang.mockstagram.comment.api.util.toUInt64Value
import sean.hwang.mockstagram.comment.domain.comment.service.CommentService

@Component
class CommentGrpcService(
    private val commentService: CommentService,
) : CommentServiceGrpcKt.CommentServiceCoroutineImplBase() {
    override suspend fun createComment(request: CreateCommentRequest): Comment {
        val comment = request.toEntity()

        return withContext(Dispatchers.IO) {
            commentService.createComment(comment)
        }.toProto()
    }

    override suspend fun deleteComment(request: DeleteCommentRequest): Empty {
        withContext(Dispatchers.IO) {
            commentService.deleteComment(
                targetId = request.targetId.notNullValue(),
                targetType = request.targetType.toDomain(),
                commenterId = request.requesterId.toLong(),
            )
        }

        return Empty.getDefaultInstance()
    }

    override suspend fun batchGetCommentStats(request: BatchGetCommentStatsRequest): BatchGetCommentStatsResponse {
        val requesterId = request.requesterId.toLongOrNull()

        val commentTargets = request.commentTargetsList.map { it.toDomain() }
        val commentCounts = withContext(Dispatchers.IO) {
            commentService.batchGetCommentCounts(commentTargets)
        }

        val comments = requesterId?.let {
            withContext(Dispatchers.IO) {
                commentService.batchGetComments(
                    commentTargets = commentTargets,
                    commenterId = it,
                )
            }
        }

        return batchGetCommentStatsResponse {
            this.commentStats += commentTargets.mapNotNull { commentTarget ->
                val commentCount = commentCounts[commentTarget]
                commentCount?.let {
                    commentStat {
                        this.target = commentTarget.toProto()
                        this.commentCount = it.toUInt64Value()
                    }
                }
            }
        }
    }
}
