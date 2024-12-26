export interface Post {
    id: string,
    author: PostAuthor,
    description: string,
    pages: PostPage[],
    createdAt: string,
    likeCount: number,
    commentCount: number,
    isLiked: boolean,
    isSaved: boolean,
}

export interface PostAuthor {
    id: number,
    username: string,
    imageUrl: string,
}

export interface PostPage {
    imageUrl: string
}

export interface UploadPostRequest {
    requesterId: number,
    post: PostForm
}

export interface PostForm {
    description: string,
    type: string,
    pages: PostPageForm[],
}

export interface PostPageForm {
    imageUrl: string
}

export interface UploadPostResponse {
    post: Post,
}

export interface ListPostsRequest {
    page: Page,
    filter: Filter,
    order: Order,
}

export enum Order {
    ORDER_UNSPECIFIED = 0,
    ORDER_NEWEST = 1,
}

export interface Filter {
    authorId?: number
}

export interface ListPostsResponse {
    posts: Post[],
    nextPageToken: string,
}

export interface Page {
    num: number,
    size: number,
}
