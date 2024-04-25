CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    successful_login_attempts INT DEFAULT 0,
    unsuccessful_login_attempts INT DEFAULT 0
);