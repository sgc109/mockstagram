package sean.hwang.mockstagram.notification.consumer.listener.comment

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sean.hwang.mockstagram.comment.event.comment.v1.CommentEvent
import sean.hwang.mockstagram.notification.consumer.util.CommentEventConverter.toDomain
import sean.hwang.mockstagram.notification.consumer.util.CommentEventConverter.toNotification
import sean.hwang.mockstagram.notification.domain.notification.repository.CommentEventRepository
import sean.hwang.mockstagram.notification.domain.notification.repository.NotificationRepository

@Service
@Transactional(readOnly = true)
class CommentEventHandler(
    private val commentEventRepository: CommentEventRepository,
    private val notificationRepository: NotificationRepository,
) {
    @Transactional
    fun handle(event: CommentEvent) {
        logger.info { "Handling comment event: $event" }

        when (event.opType) {
            CommentEvent.OpType.OP_TYPE_CREATE -> handleCreated(event)
            CommentEvent.OpType.OP_TYPE_DELETE -> handleDeleted(event)
            else -> logger.warn { "Unsupported operation type: ${event.opType}" }
        }
    }

    private fun handleCreated(event: CommentEvent) {
        logger.info { "Handling created comment event: $event" }

        val savedNotification = notificationRepository.save(event.toNotification())
        commentEventRepository.save(event.toDomain(savedNotification.id))
    }

    private fun handleDeleted(event: CommentEvent) {
        logger.info { "Handling deleted comment event: $event" }

        commentEventRepository.findByEntityId(event.commentId.toLong())?.let {
            commentEventRepository.delete(it)
            notificationRepository.deleteById(it.notificationId)
        }
    }

    companion object : KLogging()
}
