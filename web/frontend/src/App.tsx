import React, {useState} from 'react';
import FeedPage from './app/home/page/FeedPage';
import Sidebar from './app/home/page/Sidebar';
import './styles.css';
import PostDetailDialog from "./app/post/component/PostDetailDialog";

const App = () => {
        const [isDialogOpen, setIsDialogOpen] = useState(false);

        const openPostDetailDialog = () => {
            setIsDialogOpen(true);
        }
        const closeDialog = () => {
            setIsDialogOpen(false);
        }

        return (<div className="app">
                <Sidebar/>
                <FeedPage openPostDetailDialog={openPostDetailDialog}/>
                {/* 모달 컴포넌트 */}
                {isDialogOpen && <PostDetailDialog onClose={closeDialog}/>}
            </div>
        )
    }
;

export default App;