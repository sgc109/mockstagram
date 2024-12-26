import axios from 'axios';
import {CONTENT_API_URL} from '@/config'
import {ListPostsRequest, ListPostsResponse, Order, PostForm, UploadPostResponse} from '@shared/post/types';

export async function uploadPost(requesterId: number, postForm: PostForm): Promise<UploadPostResponse> {
    const request = {
        requesterId,
        post: postForm
    }
    const response = await axios.post<UploadPostResponse>(`${CONTENT_API_URL}/v1/posts`, request);

    return response.data;
}

export async function listUserPosts(authorId: number, pageSize: number, pageNum: number): Promise<ListPostsResponse> {
    const request: ListPostsRequest = {
        filter: {
            authorId
        },
        page: {
            num: pageNum || 1,
            size: pageSize || 20,
        },
        order: Order.ORDER_NEWEST,
    }
    const response = await axios.post<ListPostsResponse>(`${CONTENT_API_URL}/v1/posts:list`, request);

    return {
        posts: response.data.posts ?? [],
        nextPageToken: response.data.nextPageToken ?? '',
    }
}
