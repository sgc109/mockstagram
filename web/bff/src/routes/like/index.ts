import express, {Request, Response} from 'express';
import {like, unlike} from "./service";
import {authenticateJWT} from "@middleware/auth";

const router = express.Router();

router.post('/like', authenticateJWT, async (req: Request, res: Response) => {
    console.log(req.body);

    console.log(`req.body.targetType=${req.body.targetType}`);
    console.log(`req.body.targetId=${req.body.targetId}`);

    const form = {
        targetType: req.body.targetType,
        targetId: req.body.targetId
    }
    const response = like(req.userId!, form);

    res.json(response)
});

router.post('/unlike', authenticateJWT, async (req: Request, res: Response) => {
    console.log(req.body);

    console.log(`req.body.targetType=${req.body.targetType}`);
    console.log(`req.body.targetId=${req.body.targetId}`);

    const form = {
        targetType: req.body.targetType,
        targetId: req.body.targetId
    }
    const response = unlike(req.userId!, form);

    res.json(response)
});

export default router;
