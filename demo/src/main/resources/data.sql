CREATE DATABASE IF NOT EXISTS my_database;

USE my_database;

CREATE TABLE customer (
      id INT AUTO_INCREMENT PRIMARY KEY,
      customer_name VARCHAR(100) NOT NULL,
      age INT,
      gender VARCHAR(10),
      password VARCHAR(255) NOT NULL
);

INSERT INTO customer (customer_name, age, gender, password) VALUES ('John Doe', 30, 'Male', 'password123');
INSERT INTO customer (customer_name, age, gender, password) VALUES ('Jane Smith', 25, 'Female', 'securePassword');
INSERT INTO customer (customer_name, age, gender, password) VALUES ('Michael Brown', 40, 'Male', 'mySecretPass');
INSERT INTO customer (customer_name, age, gender, password) VALUES ('Emily Johnson', 28, 'Female', 'topSecret');
