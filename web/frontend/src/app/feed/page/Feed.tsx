import React, { useEffect, useState } from 'react';
import { getFeed } from '../api/getFeed';
import { Post as PostData } from '../../../../../shared/post/types';
import Post from '../../post/component/Post'


const Feed: React.FC = () => {
  const [posts, setPosts] = useState<PostData[]>([]);
  
  useEffect(() => {
    const fetchFeed = async () => {
      try {
        const data = await getFeed();
        setPosts(data.posts);
      } catch (err) {
        console.error('Failed to fetch feed:', err);
      }
    };
    fetchFeed();
  }, []);
  
  return (
    <div className="feed">
    {posts.map((post) => (
      <Post key={post.id} post={post} />
    ))}
    </div>
  );
};

export default Feed;