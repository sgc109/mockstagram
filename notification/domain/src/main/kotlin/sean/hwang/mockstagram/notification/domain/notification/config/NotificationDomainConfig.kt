package sean.hwang.mockstagram.notification.domain.notification.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = [PACKAGE_NAME])
@EnableJpaRepositories(basePackages = [PACKAGE_NAME])
@ComponentScan(basePackages = [PACKAGE_NAME])
class NotificationDomainConfig

private const val PACKAGE_NAME = "sean.hwang.mockstagram.notification.domain"