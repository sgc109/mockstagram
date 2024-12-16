package sean.hwang.mockstagram.reaction.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sean.hwang.mockstagram.reaction.domain.like.config.ReactionDomainConfig

@Configuration
@Import(
    ReactionDomainConfig::class,
)
class ReactionApiConfig
