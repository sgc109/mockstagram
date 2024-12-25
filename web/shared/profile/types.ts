export interface GetUserProfileResponse {
    user: User
}

export interface User {
    username: string
    fullName: string
    bio: string
    profileImage: string
}
