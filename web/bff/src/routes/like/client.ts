import axios from 'axios';
import {REACTION_API_URL} from '@/config'
import {BatchGetLikeStatsResponse, CreateLikeResponse, LikeTarget} from "@shared/like/types";

export async function createLike(requesterId: string, likeTarget: LikeTarget): Promise<CreateLikeResponse> {
    const request = {
        requesterId,
        targetType: likeTarget.type,
        targetId: likeTarget.id
    }
    const response = await axios.post<CreateLikeResponse>(`${REACTION_API_URL}/v1/like`, request);

    return response.data;
}

export async function deleteLike(requesterId: string, likeTarget: LikeTarget): Promise<void> {
    const request = {
        requesterId,
        targetType: likeTarget.type,
        targetId: likeTarget.id
    }
    await axios.post<void>(`${REACTION_API_URL}/v1/unlike`, request);
}

export async function batchGetLikeStats(requesterId: string | undefined, likeTargets: LikeTarget[]): Promise<BatchGetLikeStatsResponse> {
    const request = {
        requesterId,
        likeTargets: likeTargets
    }
    const response = await axios.post<BatchGetLikeStatsResponse>(`${REACTION_API_URL}/v1/likes:batchGetStats`, request);

    return response.data;
}
