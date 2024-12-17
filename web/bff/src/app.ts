import express, {Request, Response} from 'express';
import cors from 'cors';
import { getFeedResponse } from './data';
import postRoutes from './routes/post/index';
import authRoutes from './routes/auth/index';
import dotenv from 'dotenv';

dotenv.config();

const app = express();
app.use(cors());
app.use(express.json());

// Mock endpoint to return feed data
app.get('/api/v1/feed', (req: Request, res: Response) => {
  res.json(getFeedResponse);
});

app.use('/api/v1/posts', postRoutes);
app.use('/api/v1/auth', authRoutes);

const PORT = 8082;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
