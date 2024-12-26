import React, {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import "./ProfilePage.css";
import {getUser, listUserPosts} from "@app/profile/api/getUser";
import {User} from "@shared/profile/types";
import {Post} from "@shared/post/types";

interface ProfilePageProps {
}

const ProfilePage: React.FC<ProfilePageProps> = () => {
    const location = useLocation();
    const username = location.pathname.split('/')[1];

    const [user, setUser] = useState<User>();
    const [firstFetched, setFirstFetched] = useState(false);
    const [posts, setPosts] = useState<Post[]>([]); // State for posts
    const [pageNum, setPageNum] = useState(1); // Current page number
    const [isLoading, setIsLoading] = useState(false); // Loading state
    const [hasMore, setHasMore] = useState(true); // Whether more posts are available
    const observerRef = useRef<HTMLDivElement | null>(null); // Ref for observer

    useEffect(() => {
        const fetchUser = async () => {
            return await getUser(username)
        };
        fetchUser().then((user) => {
            setUser(user)
        }).catch((err) => {
            console.error('Failed to fetch feed:', err);
        });
    }, [username]);

    // Fetch posts whenever pageNum changes
    useEffect(() => {
        if (user === undefined || isLoading || !hasMore) {
            return;
        }
        // Function to fetch posts
        const fetchPosts = async (pageNum: number, pageSize: number) => {
            setIsLoading(true);
            return await listUserPosts(user!.id, pageNum, pageSize)
        };

        console.log('fetching posts', pageNum);
        fetchPosts(pageNum, 20).then((res) => {
            setPosts((prevPosts) => [...prevPosts, ...res.posts]);
            setHasMore(res.posts.length > 0); // Check if there are more posts
        }).catch((err) => {
            console.error("Error fetching posts:", err);
        }).finally(() => {
            setIsLoading(false);
            if (!firstFetched) {
                setFirstFetched(true);
            }
        })
    }, [user, pageNum]);

    // Observe the last element
    useEffect(() => {
        if (!firstFetched || isLoading || !hasMore) return;

        const observer = new IntersectionObserver(
            (entries) => {
                if (entries[0].isIntersecting) {
                    setPageNum((prevPageNum) => prevPageNum + 1);
                }
            },
            {threshold: 1.0}
        );

        if (observerRef.current) {
            observer.observe(observerRef.current);
        }

        return () => {
            if (observerRef.current) observer.unobserve(observerRef.current);
        };
    }, [isLoading, hasMore]);

    return (
        <div className="profile-page">
            <div className="profile-header">
                <img src={user?.thumbnailUrl} alt={user?.username} className="avatar"/>
                <div className="profile-info-section">
                    <div className="profile-username-section">
                        {user?.username}
                    </div>
                    <div className="profile-stats-section">
                    </div>
                    <div className="profile-bio-section">
                        <div className="profile-name">{user?.name}</div>
                        <div className="profile-bio">{user?.bio}</div>
                    </div>
                </div>
            </div>
            <div className="posts-section">
                <div className="post-grid">
                    {posts.map((post) => (
                        <div key={post.id} className="post-card">
                            <img src={post.pages[0].imageUrl} alt={`Post ${post.id}`}/>
                        </div>
                    ))}
                </div>
                <div ref={observerRef} className="observer">
                    {isLoading && <p>Loading more posts...</p>}
                </div>
            </div>
        </div>
    );
};

export default ProfilePage;
