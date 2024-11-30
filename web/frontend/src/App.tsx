import React, {useState} from 'react';
import FeedPage from './app/home/page/FeedPage';
import Sidebar from './app/home/page/Sidebar';
import './styles.css';
import PostDetailDialog from "./app/post/component/PostDetailDialog";
import PostUploadDialog from "./app/post/component/PostUploadDialog";

const App = () => {
        const [isPostDetailDialogOpen, setIsPostDetailDialogOpen] = useState(false);
        const [isPostUploadDialogOpen, setIsPostUploadDialogOpen] = useState(false);

        const openPostDetailDialog = () => {
            setIsPostDetailDialogOpen(true);
        }
        const closePostDetailDialog = () => {
            setIsPostDetailDialogOpen(false);
        }

        const openPostUploadDialog = () => {
            setIsPostUploadDialogOpen(true);
        }
        const closePostUploadDialog = () => {
            setIsPostUploadDialogOpen(false);
        }

        return (<div className="app">
                <Sidebar openPostUploadDialog={openPostUploadDialog}/>
                <FeedPage openPostDetailDialog={openPostDetailDialog} />
                {/* 모달 컴포넌트 */}
                {isPostDetailDialogOpen && <PostDetailDialog onClose={closePostDetailDialog}/>}
                {isPostUploadDialogOpen && <PostUploadDialog onClose={closePostUploadDialog}/>}
            </div>
        )
    }
;

export default App;