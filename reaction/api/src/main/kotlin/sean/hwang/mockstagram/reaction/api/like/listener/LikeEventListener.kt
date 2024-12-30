package sean.hwang.mockstagram.reaction.api.like.listener

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener
import sean.hwang.mockstagram.reaction.api.util.toStringValue
import sean.hwang.mockstagram.reaction.api.util.toUInt64Value
import sean.hwang.mockstagram.reaction.domain.like.entity.LikeTargetType
import sean.hwang.mockstagram.reaction.domain.like.event.LikeEvent
import sean.hwang.mockstagram.reaction.event.like.v1.likeEvent
import java.util.UUID
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent as LikeEventProto

@Component
class LikeEventListener(
    private val kafkaTemplate: KafkaTemplate<String, LikeEventProto>,
) {
    @Async
    @TransactionalEventListener
    fun listen(event: LikeEvent) {
        val eventProto = event.toProto()
        kafkaTemplate.send("likes.event", event.targetId, eventProto)
    }

    private fun LikeEvent.toProto(): LikeEventProto =
        this.let {
            likeEvent {
                this.id = UUID.randomUUID().toString()
                this.likeId = it.likeId.toString()
                this.likerId = it.likerId.toStringValue()
                this.targetId = it.targetId
                this.targetType = it.targetType.toProto()
                this.opType = it.type.toProto()
                this.count = it.count.toUInt64Value()
            }
        }

    private fun LikeTargetType.toProto(): LikeEventProto.TargetType =
        when (this) {
            LikeTargetType.POST -> LikeEventProto.TargetType.LIKE_TARGET_TYPE_POST
        }

    private fun LikeEvent.Type.toProto(): LikeEventProto.OpType =
        when (this) {
            LikeEvent.Type.CREATED -> LikeEventProto.OpType.OP_TYPE_CREATE
            LikeEvent.Type.DELETED -> LikeEventProto.OpType.OP_TYPE_DELETE
        }
}
