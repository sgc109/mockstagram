package sean.hwang.mockstagram.notification.consumer.listener.like

import mu.KLogging
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent

@Component
class LikeEventHandler {
    fun handle(event: LikeEvent) {
        logger.info { "Handling like event: $event" }
    }

    companion object : KLogging()
}
