import AWS from 'aws-sdk';

// DynamoDB 설정
const dynamoDB = new AWS.DynamoDB({
    region: process.env.AWS_REGION,
    endpoint: process.env.DYNAMODB_ENDPOINT,
});

const tableName = process.env.DYNAMODB_TABLE_NAME!;

const params = {
    TableName: tableName,
    KeySchema: [
        { AttributeName: 'filename', KeyType: 'HASH' }, // 파티션 키
    ],
    AttributeDefinitions: [
        { AttributeName: 'filename', AttributeType: 'S' },
    ],
    ProvisionedThroughput: {
        ReadCapacityUnits: 1,
        WriteCapacityUnits: 1,
    },
};

const createTable = async () => {
    try {
        const result = await dynamoDB.createTable(params).promise();
        console.log('Table created successfully:', result);
    } catch (error) {
        console.error('Error creating table:', error);
    }
};

createTable();
