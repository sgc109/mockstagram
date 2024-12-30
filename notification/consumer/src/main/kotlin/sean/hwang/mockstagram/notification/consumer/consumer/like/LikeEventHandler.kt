package sean.hwang.mockstagram.notification.consumer.consumer.like

import org.springframework.stereotype.Component
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent

@Component
class LikeEventHandler {
    fun handle(event: LikeEvent) {
        println("Handling like event: $event")
    }
}
