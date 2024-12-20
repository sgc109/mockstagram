package sean.hwang.mockstagram.user.api.user.v1.converter

import sean.hwang.mockstagram.user.api.user.v1.User
import sean.hwang.mockstagram.user.api.user.v1.user
import sean.hwang.mockstagram.user.api.util.toBoolValue
import sean.hwang.mockstagram.user.api.util.toInt64Value
import sean.hwang.mockstagram.user.api.util.toStringValue
import sean.hwang.mockstagram.user.api.util.toTimestamp

object Converters {
    fun sean.hwang.mockstagram.user.domain.user.entity.User.toProto(): User {
        val entity = this
        return user {
            this.id = entity.id.toString()
            this.username = entity.username.toStringValue()
            this.name = entity.name.toStringValue()
            entity.bio?.let { this.bio = it.toStringValue() }
            entity.imageUrl?.let { this.imageUrl = it.toStringValue() }
            entity.thumbnailUrl?.let { this.thumbnailUrl = it.toStringValue() }
            entity.websiteUrl?.let { this.websiteUrl = it.toStringValue() }
            this.postCount = entity.postCount.toInt64Value()
            this.followerCount = entity.followerCount.toInt64Value()
            this.followingCount = entity.followingCount.toInt64Value()
            this.isHidden = entity.isHidden.toBoolValue()
            this.createdAt = entity.createdAt.toTimestamp()
            this.updatedAt = entity.updatedAt.toTimestamp()
        }
    }
}
