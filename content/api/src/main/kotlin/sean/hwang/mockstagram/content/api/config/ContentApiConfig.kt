package sean.hwang.mockstagram.content.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sean.hwang.mockstagram.content.domain.post.config.ContentDomainConfig

@Configuration
@Import(
    ContentDomainConfig::class,
)
class ContentApiConfig
