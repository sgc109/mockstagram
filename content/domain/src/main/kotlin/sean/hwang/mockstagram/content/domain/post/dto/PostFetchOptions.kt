package sean.hwang.mockstagram.content.domain.post.dto

data class PostFetchOptions(
    val order: Order = Order.NEWEST,
    val filter: Filter,
) {
    enum class Order {
        NEWEST,
        OLDEST,
    }

    data class Filter(
        val authorId: String?,
    )
}
