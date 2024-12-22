import express, {Request, Response} from 'express';
import {like, unlike} from "./service";
import {authenticateJWT} from "@middleware/auth";
import {LikeTarget} from "@shared/like/types";

const router = express.Router();

router.post('/like', authenticateJWT, async (req: Request, res: Response) => {
    console.log(req.body);

    console.log(`req.body.targetType=${req.body.targetType}`);
    console.log(`req.body.targetId=${req.body.targetId}`);

    const likeTarget: LikeTarget = {
        id: req.body.targetId,
        type: req.body.targetType,
    }
    const response = await like(req.userId!, likeTarget);

    res.json(response)
});

router.post('/unlike', authenticateJWT, async (req: Request, res: Response) => {
    console.log(req.body);

    console.log(`req.body.targetType=${req.body.targetType}`);
    console.log(`req.body.targetId=${req.body.targetId}`);

    const likeTarget: LikeTarget = {
        id: req.body.targetId,
        type: req.body.targetType,
    }

    await unlike(req.userId!, likeTarget);

    res.sendStatus(200);
});

export default router;
