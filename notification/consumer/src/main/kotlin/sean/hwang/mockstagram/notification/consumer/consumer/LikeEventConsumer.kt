package sean.hwang.mockstagram.notification.consumer.consumer

import mu.KLogging
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.notification.consumer.dto.LikeEvent

@Component
@KafkaListener(topics = ["likes.event"], groupId = "notification-consumer")
class LikeEventConsumer {
    @KafkaHandler
    fun handleMessage(
        @Headers headers: MessageHeaders,
        @Payload event: LikeEvent,
    ) {
        logger.info { "message consumed: headers=${headers}, payload=$event" }
    }

    @KafkaHandler(isDefault = true)
    fun handleUnknownMessage(
        @Headers headers: MessageHeaders,
        @Payload event: Any,
    ) {
    }

    companion object : KLogging()
}
