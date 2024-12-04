package sean.hwang.mockstagram.user.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sean.hwang.mockstagram.user.domain.user.config.UserDomainConfig

@Configuration
@Import(
    UserDomainConfig::class,
)
class UserApiConfig
