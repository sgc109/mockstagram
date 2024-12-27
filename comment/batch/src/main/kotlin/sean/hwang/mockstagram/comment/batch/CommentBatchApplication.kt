package sean.hwang.mockstagram.comment.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommentBatchApplication

fun main(args: Array<String>) {
    runApplication<CommentBatchApplication>(*args)
}
