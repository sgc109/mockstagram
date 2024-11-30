import React, {useEffect, useState} from 'react';
import {getFeed} from '../api/getFeed';
import {Post as PostData} from '../../../../../shared/post/types';
import FeedPost from '../component/FeedPost'
import "./FeedPage.css";

interface FeedPageProps {
    openPostDetailDialog: () => void;
}

const FeedPage: React.FC<FeedPageProps> = ({openPostDetailDialog}) => {
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
        <div className="feed-page">
            <div className="feed">
                {posts.map((post) => (
                    <FeedPost key={post.id} post={post} openPostDetailDialog={openPostDetailDialog}/>
                ))}
            </div>
        </div>
    );
};

export default FeedPage;