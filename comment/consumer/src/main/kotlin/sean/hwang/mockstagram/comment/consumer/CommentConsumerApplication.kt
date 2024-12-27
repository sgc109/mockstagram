package sean.hwang.mockstagram.comment.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommentConsumerApplication

fun main(args: Array<String>) {
    runApplication<CommentConsumerApplication>(*args)
}
