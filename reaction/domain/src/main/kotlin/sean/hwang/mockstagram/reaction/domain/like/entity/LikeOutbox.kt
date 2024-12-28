package sean.hwang.mockstagram.reaction.domain.like.entity

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.PreRemove
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import sean.hwang.mockstagram.reaction.domain.like.util.LikeToJsonStringConverter
import java.util.UUID

@Entity
@Table(name = "likes_outbox")
data class LikeOutbox(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(name = "aggregateid", nullable = false)
    val aggregateId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: EventType,
    @Column(name = "payload", columnDefinition = "json")
    @Convert(converter = LikeToJsonStringConverter::class)
    val payload: Like? = null,
) {
    @Column(name = "aggregatetype", nullable = false)
    val aggregateType: String = "likes"

    enum class EventType {
        CREATED, DELETED
    }

    @PreUpdate
    @PreRemove
    private fun preventUpdateOrDelete() {
        throw UnsupportedOperationException("Update and Delete operations are not allowed for this entity.")
    }
}
