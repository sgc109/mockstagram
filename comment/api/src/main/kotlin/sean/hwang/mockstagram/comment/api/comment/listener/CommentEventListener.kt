package sean.hwang.mockstagram.comment.api.comment.listener

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener
import sean.hwang.mockstagram.comment.api.util.toStringValue
import sean.hwang.mockstagram.comment.api.util.toUInt64Value
import sean.hwang.mockstagram.comment.domain.comment.entity.CommentTargetType
import sean.hwang.mockstagram.comment.domain.comment.event.CommentEvent
import sean.hwang.mockstagram.comment.event.comment.v1.commentEvent
import java.util.UUID
import sean.hwang.mockstagram.comment.event.comment.v1.CommentEvent as CommentEventProto

@Component
class CommentEventListener(
    private val kafkaTemplate: KafkaTemplate<String, CommentEventProto>,
) {
    @Async
    @TransactionalEventListener
    fun listen(event: CommentEvent) {
        val eventProto = event.toProto()
        kafkaTemplate.send("comment.event", event.targetId, eventProto)
    }

    private fun CommentEvent.toProto(): CommentEventProto =
        this.let {
            commentEvent {
                this.id = UUID.randomUUID().toString()
                this.commentId = it.commentId.toString()
                this.commenterId = it.commenterId.toStringValue()
                this.targetId = it.targetId
                this.targetType = it.targetType.toProto()
                this.opType = it.type.toProto()
                this.count = it.count.toUInt64Value()
            }
        }

    private fun CommentTargetType.toProto(): CommentEventProto.TargetType =
        when (this) {
            CommentTargetType.POST -> CommentEventProto.TargetType.COMMENT_TARGET_TYPE_POST
            CommentTargetType.COMMENT -> CommentEventProto.TargetType.COMMENT_TARGET_TYPE_COMMENT
        }

    private fun CommentEvent.Type.toProto(): CommentEventProto.OpType =
        when (this) {
            CommentEvent.Type.CREATED -> CommentEventProto.OpType.OP_TYPE_CREATE
            CommentEvent.Type.DELETED -> CommentEventProto.OpType.OP_TYPE_DELETE
        }
}
