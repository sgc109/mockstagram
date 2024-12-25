import React, {useEffect, useState} from "react";
import {useLocation} from "react-router-dom";
import "./ProfilePage.css";
import {getUserProfile} from "@app/profile/api/getUserProfile";
import {User} from "@shared/profile/types";

interface ProfilePageProps {
}

// Define types for user profile
interface Post {
    id: string;
    imageUrl: string;
}

interface UserProfile {
    username: string;
    fullName: string;
    bio: string;
    profilePictureUrl: string;
    posts: Post[];
}

// Sample user data
const userProfile: UserProfile = {
    username: "spiderman",
    fullName: "Peter Parker",
    bio: "I'm Spiderman",
    profilePictureUrl: "https://via.placeholder.com/150",
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
                const data = await getUserProfile(username);
                // setUser(data.user);
            } catch (err) {
                console.error('Failed to fetch feed:', err);
            }
        };
        fetchUser();
    }, []);

    return (
        <div className="profile-page">
            <div className="profile-header">
                <img src={userProfile.profilePictureUrl} alt={userProfile.username} className="avatar"/>
                <div className="profile-info-section">
                    <div className="profile-username-section">
                        {userProfile.username}
                    </div>
                    <div className="profile-stats-section">
                    </div>
                    <div className="profile-bio-section">
                        <div className="profile-name">{userProfile.fullName}</div>
                        <div className="profile-bio">{userProfile.bio}</div>
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
