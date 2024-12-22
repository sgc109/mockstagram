import express, {Request, Response} from 'express';
import {getFeedResponse} from "@/data";
import {batchGetLikeStats} from "@/routes/like/client";
import {authenticateJWT} from "@middleware/auth";
import {LikeStat, LikeTarget, LikeTargetType} from "@shared/like/types";

const router = express.Router();

router.get('/', authenticateJWT, async (req: Request, res: Response) => {
    const postLikeTargets: LikeTarget[] = getFeedResponse.posts.map(post => {
        return {
            id: post.id,
            type: LikeTargetType.POST,
        }
    })

    // NOTE: Resolve 'likeCount' and 'isLiked' through calling reaction-api
    const likeStats = (await batchGetLikeStats(req.userId, postLikeTargets)).likeStats || []
    const likeStatsByTarget: Map<string, LikeStat> = new Map(likeStats.map(likeStat => {
            // TODO: Use a better key than stringified JSON
            return [JSON.stringify(likeStat.target), likeStat]
        }
    ))

    const likeResolvedPosts = getFeedResponse.posts.map(post => {
        const likeTarget: LikeTarget = {id: post.id, type: LikeTargetType.POST}
        const likeStat = likeStatsByTarget.get(JSON.stringify(likeTarget))
        return {
            ...post,
            likeCount: likeStat?.likeCount || 0,
            isLiked: likeStat?.isLiked || false
        }
    })
    res.json({
        posts: likeResolvedPosts
    });
});

export default router;
