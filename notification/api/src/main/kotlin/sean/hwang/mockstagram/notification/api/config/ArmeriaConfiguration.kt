package sean.hwang.mockstagram.notification.api.config

import com.linecorp.armeria.server.ServerBuilder
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.docs.DocServiceFilter
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import io.grpc.BindableService
import io.grpc.health.v1.HealthGrpc
import io.grpc.protobuf.services.ProtoReflectionService
import io.grpc.reflection.v1alpha.ServerReflectionGrpc
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ArmeriaConfiguration {
    // A user can configure the server by providing an ArmeriaServerConfigurator bean.
    @Bean
    fun armeriaServerConfigurator(bindableServices: List<BindableService>): ArmeriaServerConfigurator {
        // Customize the server using the given ServerBuilder. For example:
        return ArmeriaServerConfigurator { builder: ServerBuilder ->
            // Add DocService that enables you to send Thrift and gRPC requests
            // from web browser.
            builder.serviceUnder(
                "/docs",
                DocService
                    .builder()
                    .exclude(
                        DocServiceFilter
                            .ofServiceName(ServerReflectionGrpc.SERVICE_NAME)
                            .or(DocServiceFilter.ofServiceName(HealthGrpc.SERVICE_NAME)),
                    ).build(),
            )

            // Log every message which the server receives and responds.
            builder.decorator(LoggingService.newDecorator())

            // Write access log after completing a request.
            builder.accessLogWriter(AccessLogWriter.combined(), false)

            val grpcService =
                GrpcService
                    .builder()
                    .enableHttpJsonTranscoding(true)
                    .enableUnframedRequests(true)
                    .addService(ProtoReflectionService.newInstance())
                    .apply {
                        bindableServices.forEach(this::addService)
                    }.build()

            builder.service(grpcService)
        }
    }
}
