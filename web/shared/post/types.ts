export interface Post {
    id: number,
    author: PostAuthor,
    description: string,
    pages: Page[],
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

export interface Page {
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
