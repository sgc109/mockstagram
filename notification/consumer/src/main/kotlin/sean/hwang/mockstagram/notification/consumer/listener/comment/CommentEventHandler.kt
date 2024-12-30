package sean.hwang.mockstagram.notification.consumer.listener.comment

import mu.KLogging
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.comment.event.comment.v1.CommentEvent

@Component
class CommentEventHandler {
    fun handle(event: CommentEvent) {
        logger.info { "Handling comment event: $event" }
    }

    companion object : KLogging()
}
