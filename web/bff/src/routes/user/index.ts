import express, {Request, Response} from 'express';
import {findUser} from "@/routes/user/service";

const router = express.Router();

router.get('/:username', async (req: Request, res: Response) => {
    const response = await findUser(req.params.username);

    res.json(response)
});

export default router;
