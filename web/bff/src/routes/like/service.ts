import {createLike, deleteLike} from './client';
import {LikeForm, CreateLikeResponse} from '@shared/like/types';

export async function like(requesterId: string, likeForm: LikeForm): Promise<CreateLikeResponse> {
    const response = await createLike(requesterId, likeForm);
    return response
}

export async function unlike(requesterId: string, likeForm: LikeForm): Promise<void> {
    await deleteLike(requesterId, likeForm);
}
