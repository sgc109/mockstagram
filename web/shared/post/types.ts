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

}

export interface UploadPostResponse {

}
