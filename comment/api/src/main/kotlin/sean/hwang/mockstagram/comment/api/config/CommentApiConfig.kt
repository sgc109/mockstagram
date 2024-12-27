package sean.hwang.mockstagram.comment.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sean.hwang.mockstagram.comment.domain.comment.config.CommentDomainConfig

@Configuration
@Import(
    CommentDomainConfig::class,
)
class CommentApiConfig
