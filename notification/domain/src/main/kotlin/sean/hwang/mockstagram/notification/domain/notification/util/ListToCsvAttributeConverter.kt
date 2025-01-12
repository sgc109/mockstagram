package sean.hwang.mockstagram.notification.domain.notification.util

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ListToCsvAttributeConverter : AttributeConverter<List<Long>, String?> {
    override fun convertToDatabaseColumn(attribute: List<Long>): String? =
        attribute
            .takeIf { it.isNotEmpty() }
            ?.joinToString(",")

    override fun convertToEntityAttribute(dbData: String?): List<Long> =
        dbData
            ?.takeIf { it.isNotBlank() }
            ?.split(",")
            ?.map { it.toLong() }
            ?: emptyList()
}
