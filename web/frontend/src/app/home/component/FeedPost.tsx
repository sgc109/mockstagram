import React from 'react';
import {Post as PostData} from '../../../../../shared/post/types';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faBookmark, faComment, faHeart, faPaperPlane} from '@fortawesome/free-solid-svg-icons'
import "./FeedPost.css";

interface FeedPostProps {
    post: PostData;
    openPostDetailDialog: () => void;
}

const FeedPost: React.FC<FeedPostProps> = ({post, openPostDetailDialog}) => {
    return (
        <div className="post">
            <div className="post-header">
                <img src={post.author.imageUrl} alt={post.author.username} className="avatar"/>
                <span className="user">{post.author.username}</span>
            </div>
            <img src={post.pages[0].imageUrl} alt={post.description} className="post-image"/>

            {/* buttons below */}
            <div className="post-actions">
                <div className="action-buttons">
                    {/* 좋아요 */}
                    <button className="action-btn"><FontAwesomeIcon className='icon' icon={faHeart} inverse/></button>
                    {/* 댓글 */}
                    <button className="action-btn" onClick={openPostDetailDialog}>
                        <FontAwesomeIcon className='icon' icon={faComment} inverse/>
                    </button>
                    {/* 공유 */}
                    <button className="action-btn"><FontAwesomeIcon className='icon' icon={faPaperPlane} inverse/>
                    </button>
                </div>
                {/* 저장 */}
                <button className="save-btn"><FontAwesomeIcon className='icon' icon={faBookmark} inverse/></button>
            </div>

            <div className="post-info">
                <p className="likes">{post.likeCount} likes</p>
                <p className="caption"><strong>{post.author.username}</strong> {post.description}</p>
                <p className="comments">View all {post.commentCount} comments</p>
            </div>
        </div>
    );
};

export default FeedPost;