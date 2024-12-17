export interface Like {
    id: bigint,
    userId: string,
    targetId: string,
    targetType: LikeTargetType,
    createdAt: string,
    updatedAt: string,
}

export interface PostAuthor {
    id: number,
    username: string,
    imageUrl: string,
}

export interface Page {
    imageUrl: string
}

export interface CreateLikeRequest {
    requesterId: string,
    like: LikeForm
}

export interface LikeForm {
    targetType: LikeTargetType,
    targetId: string
}

export enum LikeTargetType {
    POST = 'LIKE_TARGET_TYPE_POST',
}

export interface CreateLikeResponse {
    like: Like
}
