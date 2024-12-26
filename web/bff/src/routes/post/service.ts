import {listUserPosts, uploadPost} from './client';
import {ListPostsResponse, PostForm, UploadPostResponse} from '@shared/post/types';

export async function createPost(requesterId: number, postForm: PostForm): Promise<UploadPostResponse> {
    const response = await uploadPost(requesterId, postForm);
    return response
}

export async function listPosts(authorId: number, pageSize: number, pageNum: number): Promise<ListPostsResponse> {
    const response = await listUserPosts(authorId, pageSize, pageNum);
    return response
}
