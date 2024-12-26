import axios from 'axios';
import {USER_API_URL} from '@/config'
import {FindUserRequest, User} from "@shared/profile/types";

export async function fetchUser(username: string): Promise<User> {
    const request: FindUserRequest = {
        filter: {
            username
        }
    }
    const response = await axios.post<User>(`${USER_API_URL}/v1/users`, request);

    return response.data;
}
