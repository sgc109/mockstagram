package sean.hwang.mockstagram.notification.consumer.listener.like

import mu.KLogging
import org.springframework.stereotype.Component
import sean.hwang.mockstagram.notification.consumer.util.LikeEventConverter.toDomain
import sean.hwang.mockstagram.notification.consumer.util.LikeEventConverter.toNotification
import sean.hwang.mockstagram.notification.domain.notification.repository.LikeEventRepository
import sean.hwang.mockstagram.notification.domain.notification.repository.NotificationRepository
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent

@Component
class LikeEventHandler(
    private val likeEventRepository: LikeEventRepository,
    private val notificationRepository: NotificationRepository,
) {
    fun handle(event: LikeEvent) {
        logger.info { "Handling like event: $event" }

        when (event.opType) {
            LikeEvent.OpType.OP_TYPE_CREATE -> handleCreated(event)
            LikeEvent.OpType.OP_TYPE_DELETE -> handleDeleted(event)
            else -> logger.warn { "Unsupported operation type: ${event.opType}" }
        }
    }

    private fun handleCreated(event: LikeEvent) {
        logger.info { "Handling created comment event: $event" }

        val foundLikeEvent = likeEventRepository.findOneByTargetIdAndTargetType(
            targetId = event.targetId,
            targetType = event.targetType.toDomain().toString(),
        )

        if (foundLikeEvent == null) { // If the like event for the target is the initial one
            val savedNotification = notificationRepository.save(event.toNotification())
            likeEventRepository.save(event.toDomain(savedNotification.id))
        } else {
            // TODO: Update notification(count, lastRaiserId, lastRaiserIds)
        }
    }

    private fun handleDeleted(event: LikeEvent) {
        logger.info { "Handling deleted like event: $event" }

        likeEventRepository.findByEntityId(event.likeId.toLong())?.let {
            likeEventRepository.delete(it)
            val notification = notificationRepository.findById(it.notificationId)
            // TODO: Update notification(count, lastRaiserId, lastRaiserIds)
        }
    }

    companion object : KLogging()
}
