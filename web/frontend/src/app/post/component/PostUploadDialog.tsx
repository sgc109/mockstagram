import React, {useMemo, useState} from 'react';
import "./PostUploadDialog.css";
import {uploadPost} from "@app/post/api/uploadPost";
import {PostForm } from "@shared/post/types";
import {USER_ID} from "@app/config";

interface PostUploadDialogProps {
    onClose: () => void;
}

const PostUploadDialog: React.FC<PostUploadDialogProps> = ({onClose}) => {
    const [imageUrl, setImageUrl] = useState<string>("https://placehold.co/400x400/000000/FFF");
    const [description, setDescription] = useState<string>("This is test description.");
    const postForm: PostForm = useMemo(() => {
        return {
            description: description,
            type: "POST_TYPE_IMAGE",
            pages: [{imageUrl: imageUrl}]
        }
    }, [imageUrl, description]);

    return (
        <div className="dimmed-overlay" onClick={onClose}>
            <div className="post-upload-dialog" onClick={(e) => e.stopPropagation()}>
                <div className="post-upload-container">
                    <div>
                        <div className="image-url-input">
                            Image URL:
                            <input type="text"
                                   defaultValue={imageUrl}
                                   onChange={(e) => setImageUrl(e.target.value)}
                            />
                        </div>
                        <div className="description-input">
                            Description:
                            <input type="text"
                                   defaultValue={description}
                                   onChange={(e) => setDescription(e.target.value)}
                            />
                        </div>
                    </div>
                    <button className="post-button" onClick={() => {
                        uploadPost({requesterId: USER_ID, postForm: postForm}).then(() => {
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
