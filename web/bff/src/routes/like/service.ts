import {createLike, deleteLike} from './client';
import {LikeTarget, CreateLikeResponse} from '@shared/like/types';

export async function like(requesterId: string, likeTarget: LikeTarget): Promise<CreateLikeResponse> {
    const response = await createLike(requesterId, likeTarget);
    return response
}

export async function unlike(requesterId: string, likeTarget: LikeTarget): Promise<void> {
    await deleteLike(requesterId, likeTarget);
}
