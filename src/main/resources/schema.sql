CREATE DATABASE IF NOT EXISTS db_pizza;
USE db_pizza;

CREATE TABLE IF NOT EXISTS pizza (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) NOT NULL,
                                     image VARCHAR(255) NOT NULL,
                                     price DECIMAL(10,2) NOT NULL,
                                     description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(100) NOT NULL,
                                    password VARCHAR(100) NOT NULL,
                                    email VARCHAR(100) NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name JSON NOT NULL,
                                      customer_name VARCHAR(100) NOT NULL,
                                      total_amount DECIMAL(10, 2) NOT NULL,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT,
                                          role_id BIGINT,
                                          PRIMARY KEY (user_id, role_id),
                                          FOREIGN KEY (user_id) REFERENCES user(id),
                                          FOREIGN KEY (role_id) REFERENCES role(id)
);

ALTER TABLE role MODIFY COLUMN id BIGINT AUTO_INCREMENT;
ALTER TABLE user_roles MODIFY COLUMN role_id BIGINT;

