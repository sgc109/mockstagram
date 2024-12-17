import { GetFeedResponse} from '@shared/feed/types'

export const getFeedResponse: GetFeedResponse = {
  posts: [
    {
      id: 1,
      author: {
        id: 1,
        username: "spiderman",
        imageUrl: "http://image.mockstagramcdn.com/my-test-bucket/spiderman-profile.png",
      },
      description: "Hello, mockstagram!",
      pages: [
        {
          imageUrl: "http://image.mockstagramcdn.com/my-test-bucket/spiderman-post.png",
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
        username: "ironman",
        imageUrl: "http://image.mockstagramcdn.com/my-test-bucket/ironman-profile.png",
      },
      description: "Nice to meet you, mockstagram!",
      pages: [
        {
          imageUrl: "http://image.mockstagramcdn.com/my-test-bucket/ironman-post.png",
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
