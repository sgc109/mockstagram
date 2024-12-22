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

export interface LikeTarget {
    id: string,
    type: LikeTargetType,
}

export enum LikeTargetType {
    POST = 'LIKE_TARGET_TYPE_POST',
}

export interface CreateLikeResponse {
    like: Like
}

export interface BatchGetLikeStatsRequest {
    requesterId: number,
    likeTargets: LikeTarget[]
}

export interface BatchGetLikeStatsResponse {
    likeStats: LikeStat[]
}

export interface LikeStat {
    target: LikeTarget,
    likeCount: number,
    isLiked: boolean
}
