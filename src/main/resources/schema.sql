DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    title VARCHAR(255),
    salary DOUBLE
);
