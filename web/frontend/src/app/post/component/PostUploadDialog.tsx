import React from 'react';
import "./PostUploadDialog.css";
import {uploadPost} from "@app/post/api/uploadPost";

interface PostUploadDialogProps {
    onClose: () => void;
}

const PostUploadDialog: React.FC<PostUploadDialogProps> = ({onClose}) => {
    return (
        <div className="dimmed-overlay" onClick={onClose}>
            <div className="post-upload-dialog" onClick={(e) => e.stopPropagation()}>
                <div className="post-upload-container">
                    <div>
                        <div className="image-url-input">
                            Image URL: <input type="text" defaultValue={"https://placehold.co/400x400/000000/FFF"}/>
                        </div>
                        <div className="description-input">
                            Description: <input type="text" defaultValue={"This is test description."}/>
                        </div>
                    </div>
                    <button className="post-button" onClick={() => {
                        uploadPost().then(() => {
                            onClose();
                        })
                    }}>
                        Post
                    </button>
                </div>
            </div>
        </div>
    );
};

export default PostUploadDialog;