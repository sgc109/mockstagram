package sean.hwang.mockstagram.notification.consumer.consumer.like

import mu.KLogging
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent

@Component
@KafkaListener(topics = ["likes.event"], groupId = "notification-consumer")
class LikeEventConsumer(
    private val likeEventHandler: LikeEventHandler,
) {
    @KafkaHandler
    fun handleMessage(
        @Headers headers: MessageHeaders,
        @Payload event: LikeEvent,
    ) {
        likeEventHandler.handle(event)
    }

    @KafkaHandler(isDefault = true)
    fun handleUnknownMessage(
        @Headers headers: MessageHeaders,
        @Payload event: Any,
    ) {
        logger.info { "Received unknown message: event=$event, headers=$headers" }
    }

    companion object : KLogging()
}
