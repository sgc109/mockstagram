import {client} from '@core/api';
import {GetFeedResponse} from '@shared/feed/types';

export const getFeed = async (): Promise<GetFeedResponse> => {
    const getFeedResponse = await client.get<GetFeedResponse>("/v1/feed");

    return getFeedResponse.data;
};
