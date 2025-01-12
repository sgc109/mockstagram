package sean.hwang.mockstagram.notification.consumer.client

import sean.hwang.mockstagram.notification.consumer.dto.PostDto

class ContentGrpcClient(
    // TODO: Inject gRPC client stub
) : ContentClient {
    override fun fetchPostById(id: Long): PostDto? {
        return null // TODO: Implement fetching post by id
    }
}
