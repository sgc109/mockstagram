import {client} from '@core/api';
import {User} from "@shared/profile/types";

export const getUser = async (username: string): Promise<User> => {
    const user = await client.get<User>(`/v1/users/${username}`);

    return user.data;
};
