package sean.hwang.mockstagram.content.api.post.v1.converter

import sean.hwang.mockstagram.content.api.post.v1.CreatePostRequest
import sean.hwang.mockstagram.content.api.post.v1.Post
import sean.hwang.mockstagram.content.api.post.v1.PostType
import sean.hwang.mockstagram.content.api.post.v1.post
import sean.hwang.mockstagram.content.api.post.v1.postPage
import sean.hwang.mockstagram.content.api.util.notNullValue
import sean.hwang.mockstagram.content.api.util.toBoolValue
import sean.hwang.mockstagram.content.api.util.toInt32Value
import sean.hwang.mockstagram.content.api.util.toInt64Value
import sean.hwang.mockstagram.content.api.util.toStringValue

object Converters {
    fun sean.hwang.mockstagram.content.domain.post.document.Post.toProto(): Post {
        val document = this
        return post {
            this.id = document.id.toString()
            this.authorId = document.authorId.toStringValue()
            this.likeCount = document.likeCount.toInt64Value()
            this.saveCount = document.saveCount.toInt64Value()
            this.commentCount = document.commentCount.toInt64Value()
            this.shareCount = document.shareCount.toInt64Value()
            this.viewCount = document.viewCount.toInt64Value()
            document.description?.let { this.description = it.toStringValue() }
            this.pages +=
                document.pages.map {
                    postPage {
                        this.position = it.position
                        this.imageUrl = it.imageUrl.toStringValue()
                    }
                }
            this.type = document.type.toProto()
            document.width?.let { this.width = it.toInt32Value() }
            document.height?.let { this.height = it.toInt32Value() }
            this.onHide = document.onHide.toBoolValue()
            this.removed = document.removed.toBoolValue()
            this.visibleOnFeed = document.visibleOnFeed.toBoolValue()
        }
    }

    fun CreatePostRequest.toDocument(): sean.hwang.mockstagram.content.domain.post.document.Post {
        return sean.hwang.mockstagram.content.domain.post.document.Post(
            authorId = requesterId.notNullValue(),
            description = post.description.notNullValue(),
            type = post.type.toDomain(),
            pages = post.pagesList.mapIndexed { idx, page ->
                sean.hwang.mockstagram.content.domain.post.document.PostPage(
                    position = idx,
                    imageUrl = page.imageUrl.value,
                )
            },
        )
    }

    private fun PostType.toDomain() =
        when (this) {
            PostType.POST_TYPE_IMAGE -> sean.hwang.mockstagram.content.domain.post.document.PostType.IMAGE
            PostType.POST_TYPE_VIDEO -> sean.hwang.mockstagram.content.domain.post.document.PostType.VIDEO
            PostType.POST_TYPE_UNSPECIFIED, PostType.UNRECOGNIZED ->
                throw IllegalArgumentException("Invalid PostType: $this")
        }

    private fun sean.hwang.mockstagram.content.domain.post.document.PostType.toProto() =
        when (this) {
            sean.hwang.mockstagram.content.domain.post.document.PostType.IMAGE -> PostType.POST_TYPE_IMAGE
            sean.hwang.mockstagram.content.domain.post.document.PostType.VIDEO -> PostType.POST_TYPE_VIDEO
        }
}
