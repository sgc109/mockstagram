import {NextFunction, Request, Response} from 'express';
import jwt, {JwtPayload} from 'jsonwebtoken';

export const authenticateJWT = async (req: Request, res: Response, next: NextFunction) => {
    const authHeader = req.headers.authorization;

    if (authHeader && !authHeader.startsWith('Bearer ')) {
        res.status(403).json({message: 'Authorization header is invalid'});
        return;
    }

    const token = authHeader?.split(' ')[1];

    try {
        if (token) {
            const decoded = jwt.verify(token, process.env.SECRET_KEY!) as JwtPayload;
            req.userId = decoded.sub;
            next();
        } else {
            next();
        }
    } catch (error) {
        res.status(403).json({message: 'Invalid or expired token'});
    }
};
