import {client} from '@core/api';
import {GetFeedResponse} from '@shared/feed/types';
import {MOCK_JWT} from "@app/config";

export const getFeed = async (): Promise<GetFeedResponse> => {
    const getFeedResponse = await client.get<GetFeedResponse>("/v1/feed", {
        headers: {Authorization: `Bearer ${MOCK_JWT}`}
    });

    return getFeedResponse.data;
};
