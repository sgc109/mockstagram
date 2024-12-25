import {client} from '@core/api';
import {GetUserProfileResponse} from "@shared/profile/types";

export const getUserProfile = async (username: string): Promise<GetUserProfileResponse> => {
    const getUserProfileResponse = await client.get<GetUserProfileResponse>(`/v1/profile/${username}`);

    return getUserProfileResponse.data;
};
