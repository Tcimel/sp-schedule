CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(200) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE schedules (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(200) NOT NULL,
                           content VARCHAR(500),
                           user_id BIGINT NOT NULL,
                           created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                           modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES users(id)
);