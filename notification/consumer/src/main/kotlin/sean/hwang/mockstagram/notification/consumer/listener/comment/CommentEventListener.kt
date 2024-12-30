package sean.hwang.mockstagram.notification.consumer.listener.comment

import mu.KLogging
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.comment.event.comment.v1.CommentEvent

@Component
@KafkaListener(topics = ["comment.event"], containerFactory = "commentEventListenerContainerFactory")
class CommentEventListener(
    private val commentEventHandler: CommentEventHandler,
) {
    @KafkaHandler
    fun handleMessage(
        @Headers headers: MessageHeaders,
        @Payload event: CommentEvent,
    ) {
        commentEventHandler.handle(event)
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
