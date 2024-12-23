import * as process from "node:process";

export const CONTENT_API_URL = process.env.CONTENT_API_URL || 'http://localhost:50051';
export const REACTION_API_URL = process.env.REACTION_API_URL || 'http://localhost:50053';
export const USER_API_URL = process.env.USER_API_URL  || 'http://localhost:50054';
