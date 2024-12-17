import axios from 'axios';
import {REACTION_API_URL} from '@/config'
import {CreateLikeResponse, LikeForm} from "@shared/like/types";

export async function createLike(requesterId: string, likeForm: LikeForm): Promise<CreateLikeResponse> {
    const request = {
        requesterId,
        targetType: likeForm.targetType,
        targetId: likeForm.targetId
    }
    const response = await axios.post<CreateLikeResponse>(`${REACTION_API_URL}/v1/likes`, request);

    return response.data;
}
