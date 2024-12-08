import {client} from '@core/api';
import {PostForm, UploadPostResponse} from "@shared/post/types";
import {POST_URL_PREFIX} from "@app/post/constants";

interface Params {
    requesterId: number;
    postForm: PostForm;
}

export const uploadPost = async ({requesterId, postForm}: Params): Promise<UploadPostResponse> => {
    const request = {
        requesterId: requesterId,
        post: postForm
    }
    const getFeedResponse = await client.post<UploadPostResponse>(`${POST_URL_PREFIX}`, request);
    return getFeedResponse.data;
};
