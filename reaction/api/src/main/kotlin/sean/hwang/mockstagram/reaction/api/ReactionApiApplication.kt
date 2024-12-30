package sean.hwang.mockstagram.reaction.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class ReactionApiApplication

fun main(args: Array<String>) {
    runApplication<ReactionApiApplication>(*args)
}
