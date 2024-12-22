import express from 'express';
import cors from 'cors';
import postRoutes from './routes/post/index';
import authRoutes from './routes/auth/index';
import likeRoutes from './routes/like/index';
import feedRoutes from './routes/feed/index';
import dotenv from 'dotenv';

dotenv.config();

const app = express();
app.use(cors());
app.use(express.json());

// Mock endpoint to return feed data
app.use('/api/v1/feed', feedRoutes);
app.use('/api/v1', likeRoutes);
app.use('/api/v1/posts', postRoutes);
app.use('/api/v1/auth', authRoutes);

const PORT = 8082;
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
