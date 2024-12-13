import { Router } from 'express';
import multer from 'multer';
import { uploadFileToS3 } from '../services/s3';
import { saveMetadata } from '../services/database';

const router = Router();

// Configure multer
const storage = multer.memoryStorage();
const upload = multer({ storage });

// POST: Upload an image
router.post('/', upload.single('image'), async (req, res): Promise<void> => {
    try {
        const file = req.file;

        if (!file) {
            res.status(400).send({ error: 'No file uploaded' });
            return;
        }

        // Upload file to S3
        const s3Result = await uploadFileToS3(file);

        // Save metadata to MongoDB
        const metadata = {
            filename: file.originalname,
            mimeType: file.mimetype,
            size: file.size,
            s3Url: s3Result.Location,
        };
        // await saveMetadata(metadata);

        const newHost = "image.mockstagramcdn.com";
        const cdnUrl = metadata.s3Url.replace(/^http:\/\/[^\/]+/, `http://${newHost}`);

        res.status(200).send({ message: 'File uploaded successfully', metadata, cdnUrl });
    } catch (error) {
        console.error(error);
        res.status(500).send({ error: 'An error occurred while uploading the file' });
    }
});

export default router;
