import express, {Request, Response} from 'express';
import {UploadPostRequest} from "@shared/post/types";
import {createPost} from "./service";

const router = express.Router();

router.post('/', async (req: Request, res: Response) => {
    console.log(req.body);
    const request: UploadPostRequest = {
        requesterId: req.body.requesterId,
        post: req.body.post
    }

    const response = createPost(request.requesterId, request.post);

    res.json(response)
});

export default router;
