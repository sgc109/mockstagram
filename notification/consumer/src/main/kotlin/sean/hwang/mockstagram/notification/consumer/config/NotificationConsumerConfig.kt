package sean.hwang.mockstagram.notification.consumer.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import sean.hwang.mockstagram.notification.domain.notification.config.NotificationDomainConfig

@Configuration
@Import(
    NotificationDomainConfig::class,
)
class NotificationConsumerConfig
