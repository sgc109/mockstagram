package sean.hwang.mockstagram.notification.consumer.client

import sean.hwang.mockstagram.notification.consumer.dto.PostDto

interface ContentClient {
    fun fetchPostById(id: Long): PostDto?
}
