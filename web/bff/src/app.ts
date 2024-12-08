import express from 'express';
import cors from 'cors';
import { getFeedResponse } from './data.js';
import postRoutes from './routes/post/index.js';

const app = express();
app.use(cors());
app.use(express.json());

// Mock endpoint to return feed data
app.get('/api/v1/feed', (req, res) => {
  res.json(getFeedResponse);
});

app.use('/api/v1/posts', postRoutes);

const PORT = 8082;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});
