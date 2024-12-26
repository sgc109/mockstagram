import {client} from '@core/api';
import {User} from "@shared/profile/types";
import {ListPostsResponse} from "@shared/post/types";

export const getUser = async (username: string): Promise<User> => {
    const user = await client.get<User>(`/v1/users/${username}`);

    return user.data;
};

export const listUserPosts = async (userId: string, pageNum: number, pageSize: number): Promise<ListPostsResponse> => {
    const res = await client.get<ListPostsResponse>(`/v1/users/${userId}/posts?num=${pageNum}&size=${pageSize}`);
    return res.data;
}
