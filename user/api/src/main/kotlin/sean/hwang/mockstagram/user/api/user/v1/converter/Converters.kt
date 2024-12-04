package sean.hwang.mockstagram.user.api.user.v1.converter

import sean.hwang.mockstagram.user.api.user.v1.User
import sean.hwang.mockstagram.user.api.user.v1.user
import sean.hwang.mockstagram.user.api.util.toBoolValue
import sean.hwang.mockstagram.user.api.util.toInt64Value
import sean.hwang.mockstagram.user.api.util.toStringValue
import sean.hwang.mockstagram.user.api.util.toTimestamp

object Converters {
    fun sean.hwang.mockstagram.user.domain.user.document.User.toProto(): User {
        val document = this
        return user {
            this.id = document.id.toString()
            this.username = document.username.toStringValue()
            this.name = document.name.toStringValue()
            document.bio?.let { this.bio = it.toStringValue() }
            document.imageUrl?.let { this.imageUrl = it.toStringValue() }
            document.thumbnailUrl?.let { this.thumbnailUrl = it.toStringValue() }
            document.websiteUrl?.let { this.websiteUrl = it.toStringValue() }
            this.postCount = document.postCount.toInt64Value()
            this.followerCount = document.followerCount.toInt64Value()
            this.followingCount = document.followingCount.toInt64Value()
            this.isHidden = document.isHidden.toBoolValue()
            document.createdAt?.let { this.createdAt = it.toTimestamp() }
            document.updatedAt?.let { this.updatedAt = it.toTimestamp() }
        }
    }
}
