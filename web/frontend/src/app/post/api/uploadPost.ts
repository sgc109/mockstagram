import {client} from '@core/api';
import {FEED_URL_PREFIX} from "@app/home/constants";
import {UploadPostRequest, UploadPostResponse} from "@shared/post/types";

export const uploadPost = async (): Promise<UploadPostResponse> => {
    const getFeedResponse = await client.post<UploadPostRequest>(FEED_URL_PREFIX);
    return getFeedResponse.data;
};