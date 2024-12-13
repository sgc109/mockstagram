import AWS from 'aws-sdk';
import { ManagedUpload } from 'aws-sdk/clients/s3';
import * as dotenv from 'dotenv';
dotenv.config();

const s3 = new AWS.S3({
    endpoint: process.env.S3_ENDPOINT,
    credentials: {
        accessKeyId: process.env.AWS_ACCESS_KEY_ID!,
        secretAccessKey: process.env.AWS_SECRET_ACCESS_KEY!
    },
    region: process.env.AWS_REGION,
    s3ForcePathStyle: true,
});

export const uploadFileToS3 = (file: Express.Multer.File): Promise<ManagedUpload.SendData> => {
    const params = {
        Bucket: process.env.S3_BUCKET_NAME!,
        Key: `${Date.now()}_${file.originalname}`,
        Body: file.buffer,
        ContentType: file.mimetype,
    };

    return s3.upload(params).promise();
};
