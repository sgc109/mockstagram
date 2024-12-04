package sean.hwang.mockstagram.user.domain.user.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories(basePackages = [PACKAGE_NAME])
@ComponentScan(basePackages = [PACKAGE_NAME])
class UserDomainConfig

private const val PACKAGE_NAME = "sean.hwang.mockstagram.user.domain"
