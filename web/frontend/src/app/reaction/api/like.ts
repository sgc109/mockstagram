import {client} from '@core/api';
import {LIKE_URL_PREFIX, UNLIKE_URL_PREFIX} from "@app/post/constants";
import {CreateLikeRequest, CreateLikeResponse, DeleteLikeRequest} from "@shared/like/types";
import {MOCK_JWT} from "@app/config";

export enum LikeTargetType {
    POST = 'LIKE_TARGET_TYPE_POST',
}

interface Params {
    postId: string;
}

export const createPostLike = async ({postId}: Params): Promise<void> => {
    const request: CreateLikeRequest = {
        targetType: LikeTargetType.POST,
        targetId: postId
    }
    await client.post<CreateLikeResponse>(`${LIKE_URL_PREFIX}`, request, {headers: {'Authorization': `Bearer ${MOCK_JWT}`}});
};

export const deletePostLike = async ({postId}: Params): Promise<void> => {
    const request: DeleteLikeRequest = {
        targetType: LikeTargetType.POST,
        targetId: postId
    }
    await client.post<void>(`${UNLIKE_URL_PREFIX}`, request, {headers: {'Authorization': `Bearer ${MOCK_JWT}`}});
};
