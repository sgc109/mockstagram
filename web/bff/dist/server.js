import express from 'express';
import cors from 'cors';
import { feedData } from './data.js';
const app = express();
app.use(cors());
// Mock endpoint to return feed data
app.get('/api/feed', (req, res) => {
    res.json(feedData);
});
const PORT = 8080;
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
