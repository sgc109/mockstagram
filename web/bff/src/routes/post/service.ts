import { uploadPost } from './client.js';
import {PostForm, UploadPostRequest, UploadPostResponse} from '@shared/post/types';

export async function createPost(requesterId: number, postForm: PostForm): Promise<UploadPostResponse> {
    const response = await uploadPost(requesterId, postForm);
    return response
}
