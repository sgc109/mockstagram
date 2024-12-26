import React, {useEffect, useState} from 'react';
import {getFeed} from '../api/getFeed';
import {Post as PostData} from '../../../../../shared/post/types';
import FeedPost from '../component/FeedPost'
import "./FeedPage.css";
import {createPostLike, deletePostLike} from "@app/reaction/api/like";

interface FeedPageProps {
    openPostDetailDialog: () => void;
}

const FeedPage: React.FC<FeedPageProps> = ({openPostDetailDialog}) => {
    const [posts, setPosts] = useState<PostData[]>([]);

    const likePost = (targetPostId: string) => {
        createPostLike({postId: targetPostId}).then(() => {
            setPosts((prevPosts) => {
                return prevPosts.map((post) => {
                    console.log(post.id, targetPostId)
                    if (post.id === targetPostId) {
                        return {
                            ...post,
                            isLiked: true,
                            likeCount: post.likeCount + 1
                        }
                    }
                    return {...post};
                })
            });
        })
    }

    const unlikePost = (targetPostId: string) => {
        deletePostLike({postId: targetPostId}).then(() => {
            setPosts((prevPosts) => {
                return prevPosts.map((post) => {
                    if (post.id === targetPostId) {
                        return {
                            ...post,
                            isLiked: false,
                            likeCount: post.likeCount - 1
                        }
                    }
                    return {...post};
                })
            });
        })
    }

    useEffect(() => {
        const fetchFeed = () => {
            getFeed().then((data) => {
                setPosts(data.posts);
            }).catch((err) => {
                console.error('Failed to fetch feed:', err);
            })
        };
        fetchFeed()
    }, []);

    return (
        <div className="feed-page">
            <div className="feed">
                {posts.map((post) => (
                    <FeedPost key={post.id} post={post} openPostDetailDialog={openPostDetailDialog} likePost={likePost}
                              unlikePost={unlikePost}/>
                ))}
            </div>
        </div>
    );
};

export default FeedPage;
