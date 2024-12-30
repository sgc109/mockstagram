package sean.hwang.mockstagram.comment.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class CommentApiApplication

fun main(args: Array<String>) {
    runApplication<CommentApiApplication>(*args)
}
