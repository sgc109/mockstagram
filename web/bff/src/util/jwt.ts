import jwt from 'jsonwebtoken';

export const generateToken = (payload: object): string => {
    const expiration = Math.floor(Date.now() / 1000) + 10 * 365 * 24 * 60 * 60; // 10 years from now

    return jwt.sign(payload, process.env.SECRET_KEY!, {
        algorithm: 'HS256',
        expiresIn: expiration,
    });
};
