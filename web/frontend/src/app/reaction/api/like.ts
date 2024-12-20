import {client} from '@core/api';
import {LIKE_URL_PREFIX, UNLIKE_URL_PREFIX} from "@app/post/constants";
import {CreateLikeRequest, CreateLikeResponse, DeleteLikeRequest, LikeTargetType} from "@shared/like/types";
import {JWT} from "@app/config";

interface Params {
    requesterId: number;
    postId: string;
}

export const likePost = async ({postId}: Params): Promise<void> => {
    const request: CreateLikeRequest = {
        targetType: LikeTargetType.POST,
        targetId: postId
    }
    await client.post<CreateLikeResponse>(`${LIKE_URL_PREFIX}`, request, {headers: {'Authorization': `Bearer ${JWT}`}});
};

export const unlikePost = async ({postId}: Params): Promise<void> => {
    const request: DeleteLikeRequest = {
        targetType: LikeTargetType.POST,
        targetId: postId
    }
    await client.post<void>(`${UNLIKE_URL_PREFIX}`, request, {headers: {'Authorization': `Bearer ${JWT}`}});
};
