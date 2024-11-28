import { GetFeedResponse} from '../../shared/feed/types'

export const getFeedResponse: GetFeedResponse = {
  posts: [
    {
      id: 1,
      author: {
        id: 1,
        username: "user1",
        imageUrl: "https://placehold.co/50x50",
      },
      description: "Hello, mockstagram!",
      pages: [
        {
          imageUrl: "https://placehold.co/300x300",
        }
      ],
      createdAt: "3h",
      likeCount: 2,
      commentCount: 3,
      isLiked: false,
      isSaved: false,
    },
    {
      id: 2,
      author: {
        id: 2,
        username: "user2",
        imageUrl: "https://placehold.co/50x50",
      },
      description: "Nice to meet you, mockstagram!",
      pages: [
        {
          imageUrl: "https://placehold.co/300x300",
        }
      ],
      createdAt: "4h",
      likeCount: 0,
      commentCount: 1,
      isLiked: true,
      isSaved: true,
    }
  ],
  nextPageToken: "",
}