import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import "./ProfilePage.css";
import {getUser} from "@app/profile/api/getUser";
import {User} from "@shared/profile/types";

interface ProfilePageProps {
}

// Define types for user profile
interface Post {
    id: string;
    imageUrl: string;
}

interface UserProfile {
    posts: Post[];
}

// Sample user data
const userProfile: UserProfile = {
    posts: [
        {id: "1", imageUrl: "https://via.placeholder.com/500x500"},
        {id: "2", imageUrl: "https://via.placeholder.com/500x500"},
        {id: "3", imageUrl: "https://via.placeholder.com/500x500"},
        {id: "4", imageUrl: "https://via.placeholder.com/500x500"},
        {id: "5", imageUrl: "https://via.placeholder.com/500x500"},
    ]
};
const ProfilePage: React.FC<ProfilePageProps> = () => {
    const location = useLocation();
    const username = location.pathname.split('/')[1];

    const [user, setUser] = useState<User>();

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const user = await getUser(username);
                setUser(user);
            } catch (err) {
                console.error('Failed to fetch feed:', err);
            }
        };
        fetchUser();
    }, []);

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
                    {userProfile.posts.map((post) => (
                        <div key={post.id} className="post-card">
                            <img src={post.imageUrl} alt={`Post ${post.id}`}/>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default ProfilePage;
