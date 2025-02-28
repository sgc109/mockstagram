export interface FindUserRequest {
    filter: Filter
}

export interface Filter {
    username: string
}

export interface GetUserProfileResponse {
    user: User
}

export interface User {
    id: string,
    username: string
    name: string
    bio: string
    thumbnailUrl: string
}
