import express, {Request, Response} from 'express';
import {generateToken} from "@util/jwt";
import {authenticateJWT} from "@middleware/auth";

const router = express.Router();

router.post('/token', async (req: Request, res: Response) => {
    const { userId } = req.body;

    if (!userId) {
        res.status(400).json({ message: 'userId is required' });
        return
    }

    const token = generateToken({ sub: userId });
    res.json({ token });
});

// 보호된 경로
router.get('/protected', authenticateJWT, async (req: Request, res: Response) => {
    res.json({
        message: 'You have accessed a protected route!',
        userId: req.userId,
    });
});

export default router;
