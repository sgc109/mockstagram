import {User} from "@shared/profile/types";
import {fetchUser} from "@/routes/user/client";

export async function findUser(username: string): Promise<User> {
    const response = await fetchUser(username);
    return response
}
