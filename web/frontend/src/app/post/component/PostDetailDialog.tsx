import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBookmark, faComment, faHeart, faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import './PostDetailDialog.css';

const PostDetailDialog: React.FC<{ onClose: () => void }> = ({ onClose }) => {
    return (
        <div className="dimmed-overlay" onClick={onClose}>
            <div className="post-detail-dialog" onClick={(e) => e.stopPropagation()}>
                <div className="post-content-container">
                    {/* Image on the left */}
                    <div className="post-image-container post-left">
                        <img src="https://placehold.co/300x300" alt="FeedPost" className="post-image" />
                    </div>

                    {/* Post details on the right */}
                    <div className="post-details post-right">
                        <div className="profile-info">
                            <img src="https://placehold.co/50x50" alt="Profile" className="profile-thumbnail" />
                            <span className="username">Username</span>
                        </div>

                        <div className="post-content">
                            <p>FeedPost content goes here. This is the description of the post.</p>
                        </div>

                        <div className="comments-list">
                            {/* Add comments dynamically here */}
                            <div className="comment">
                                <span className="comment-author">Commenter 1</span>
                                <p className="comment-text">This is a comment</p>
                            </div>
                            {/* Pagination for comments */}
                        </div>

                        {/* Action buttons */}
                        <div className="post-actions">
                            <div className="action-buttons">
                                <button className="action-btn"><FontAwesomeIcon className="icon" icon={faHeart} inverse /></button>
                                <button className="action-btn"><FontAwesomeIcon className="icon" icon={faComment} inverse /></button>
                                <button className="action-btn"><FontAwesomeIcon className="icon" icon={faPaperPlane} inverse /></button>
                            </div>
                            <button className="save-btn"><FontAwesomeIcon className="icon" icon={faBookmark} inverse /></button>
                        </div>

                        <div className="post-footer">
                            <span>Likes: 123</span>
                            <span>Posted on: 2024-11-30</span>
                        </div>

                        <div className="comment-input">
                            <input type="text" placeholder="Add a comment..."/>
                            <button className="post-button" >
                                Post
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PostDetailDialog;