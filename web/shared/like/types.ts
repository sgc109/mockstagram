export interface Like {
    id: bigint,
    userId: string,
    targetId: string,
    targetType: LikeTargetType,
    createdAt: string,
    updatedAt: string,
}

export interface CreateLikeRequest {
    targetType: LikeTargetType,
    targetId: string
}

export interface DeleteLikeRequest {
    targetType: LikeTargetType,
    targetId: string
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
