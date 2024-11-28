import { Post } from '../post/types'

export interface GetFeedResponse {
    posts: Post[],
    nextPageToken: string;
}