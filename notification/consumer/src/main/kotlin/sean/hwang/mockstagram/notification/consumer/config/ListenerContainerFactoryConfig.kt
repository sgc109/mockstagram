package sean.hwang.mockstagram.notification.consumer.config

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import sean.hwang.mockstagram.comment.event.comment.v1.CommentEvent
import sean.hwang.mockstagram.reaction.event.like.v1.LikeEvent
import kotlin.reflect.jvm.jvmName

@Configuration
class ListenerContainerFactoryConfig {
    @Bean
    fun likeEventListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory =
            ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.setConsumerFactory(likeConsumerFactory())
        return factory
    }

    @Bean
    fun commentEventListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory =
            ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.setConsumerFactory(commentConsumerFactory())
        return factory
    }

    @Bean
    fun likeConsumerFactory(): ConsumerFactory<String, Any> {
        val config = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "http://localhost:19091",
            ConsumerConfig.GROUP_ID_CONFIG to "notification-consumer",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer",
            AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to "http://localhost:8086",
            KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE to LikeEvent::class.jvmName,
        )
        return DefaultKafkaConsumerFactory(config)
    }

    @Bean
    fun commentConsumerFactory(): ConsumerFactory<String, Any> {
        val config = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "http://localhost:19091",
            ConsumerConfig.GROUP_ID_CONFIG to "notification-consumer",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer",
            AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to "http://localhost:8086",
            KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE to CommentEvent::class.jvmName,
        )
        return DefaultKafkaConsumerFactory(config)
    }
}
