db = connect("mongodb://root:root@mongodb:27017/admin");

// 사용자 계정 목록
const users = [
    { username: "comment_user", password: "comment_pw", database: "comment" },
    { username: "content_user", password: "content_pw", database: "content" },
    { username: "reaction_user", password: "reaction_pw", database: "reaction" },
    { username: "notification_user", password: "notification_pw", database: "notification" },
    { username: "push_user", password: "push_pw", database: "push" },
    { username: "user_user", password: "user_pw", database: "user" },
];

// 사용자 생성 및 권한 부여
users.forEach(({ username, password, database }) => {
    db = db.getSiblingDB(database); // 대상 데이터베이스
    db.createUser({
        user: username,
        pwd: password,
        roles: [{ role: "readWrite", db: database }],
    });
    print(`Created user ${username} for database ${database}`);
});

// Initiate replica set
rs.initiate({
    _id: "rs0",
    members: [
        { _id: 0, host: "mongodb:27017" }
    ]
});
