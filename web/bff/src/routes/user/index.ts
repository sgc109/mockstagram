import express, {Request, Response} from 'express';
import {findUser} from "@/routes/user/service";
import {listPosts} from "@/routes/post/service";

const router = express.Router();

router.get('/:username', async (req: Request, res: Response) => {
    const response = await findUser(req.params.username);

    res.json(response)
});

router.get('/:userId/posts', async (req: Request, res: Response) => {
    const pageSize = Number(req.query.size) || 20;
    const pageNum = Number(req.query.num) || 1;
    const response = await listPosts(Number(req.params.userId), pageSize, pageNum);
    console.log("resopnse", response)

    res.json(response)
});

export default router;
