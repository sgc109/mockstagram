import express from 'express';
import cors from 'cors';
import { getFeedResponse } from './data.js';

const app = express();
app.use(cors());

// Mock endpoint to return feed data
app.get('/api/feed', (req, res) => {
  res.json(getFeedResponse);
});

const PORT = 8082;
app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});