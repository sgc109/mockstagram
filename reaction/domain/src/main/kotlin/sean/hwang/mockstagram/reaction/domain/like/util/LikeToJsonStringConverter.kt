package sean.hwang.mockstagram.reaction.domain.like.util

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import sean.hwang.mockstagram.reaction.domain.like.entity.Like

@Converter
class LikeToJsonStringConverter(
    private val objectMapper: ObjectMapper,
) : AttributeConverter<Like, String> {
    override fun convertToDatabaseColumn(obj: Like): String {
        return objectMapper.writeValueAsString(obj)
    }

    override fun convertToEntityAttribute(jsonStr: String): Like{
        return objectMapper.readValue(jsonStr, Like::class.java)
    }
}
