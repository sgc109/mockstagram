import React from 'react';
import { Post as PostData }  from '../../../../../shared/post/types';

interface PostProps {
  post: PostData;
}

const Post: React.FC<PostProps> = ({ post }) => {
  return (
    <div className="post">
      <div className="post-header">
        <img src={post.author.imageUrl} alt={post.author.username} className="avatar" />
        <span className="user">{post.author.username}</span>
      </div>
      <img src={post.pages[0].imageUrl} alt={post.description} className="post-image" />
      <div className="post-info">
        <p className="likes">{post.likeCount} likes</p>
        <p className="caption"><strong>{post.author.username}</strong> {post.description}</p>
        <p className="comments">View all {post.commentCount} comments</p>
      </div>
    </div>
  );
};

export default Post;