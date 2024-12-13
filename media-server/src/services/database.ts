import AWS from 'aws-sdk';

// DynamoDB 설정
const dynamoDB = new AWS.DynamoDB.DocumentClient({
    region: process.env.AWS_REGION,
    endpoint: process.env.DYNAMODB_ENDPOINT, // Local DynamoDB endpoint
});

const tableName = process.env.DYNAMODB_TABLE_NAME!;

export const saveMetadata = async (metadata: Record<string, any>) => {
    try {
        const params = {
            TableName: tableName,
            Item: metadata,
        };
        await dynamoDB.put(params).promise();
        console.log('Metadata saved to DynamoDB:', metadata);
    } catch (error) {
        console.error('Error saving metadata to DynamoDB:', error);
        throw error;
    }
};
