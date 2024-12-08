import axios from 'axios';
import {CONTENT_API_URL} from '../../config.js'
import {PostForm, UploadPostResponse} from '@shared/post/types';

export async function uploadPost(requesterId: number, postForm: PostForm): Promise<UploadPostResponse> {
    const request = {
        requesterId,
        post: postForm
    }
    const response = await axios.post<UploadPostResponse>(`${CONTENT_API_URL}/v1/posts`, request);

    return response.data;
}
