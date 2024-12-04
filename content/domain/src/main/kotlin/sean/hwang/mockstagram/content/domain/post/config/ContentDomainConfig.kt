package sean.hwang.mockstagram.content.domain.post.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories(basePackages = [PACKAGE_NAME])
@ComponentScan(basePackages = [PACKAGE_NAME])
class ContentDomainConfig

private const val PACKAGE_NAME = "sean.hwang.mockstagram.content.domain"
