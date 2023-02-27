DROP DATABASE computer_shop;

CREATE DATABASE IF NOT EXISTS computer_shop;
SHOW DATABASES;
USE computer_shop;

CREATE 	TABLE IF NOT EXISTS customer(
    cusId VARCHAR(6) NOT NULL,
    name VARCHAR(30),
    address VARCHAR(30),
    contact VARCHAR(10),
    CONSTRAINT PRIMARY KEY(cusId)
    );
DESC customer;

CREATE TABLE IF NOT EXISTS orders(
    ordId VARCHAR(6) NOT NULL,
    date DATE,
    cusId VARCHAR(6),
    CONSTRAINT PRIMARY KEY(ordId),
    CONSTRAINT FOREIGN KEY(ordId) REFERENCES customer(cusId)
    ON DELETE CASCADE ON UPDATE CASCADE
    );
DESC orders;

CREATE TABLE IF NOT EXISTS item(
    itemId VARCHAR(6) NOT NULL,
    description VARCHAR(60),
    unitPrice DECIMAL(8,2),
    qtyOnhand INT(6),
    CONSTRAINT PRIMARY KEY(itemId)
    );
DESC item;

CREATE TABLE IF NOT EXISTS orderDetails(
    ordId VARCHAR(6),
    itemId VARCHAR(6),
    qty INT(15),
    unitPrice DECIMAL(8,2),
    CONSTRAINT PRIMARY KEY(ordId,itemId),
    CONSTRAINT FOREIGN KEY(ordId) REFERENCES orders(ordId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(itemId) REFERENCES item(itemId) ON DELETE CASCADE ON UPDATE CASCADE
    );
DESC orderDetails;

CREATE TABLE IF NOT EXISTS supplyer(
    supId VARCHAR(6) NOT NULL,
    name VARCHAR(30),
    address VARCHAR(50),
    model VARCHAR(40),
    CONSTRAINT PRIMARY KEY(supId)
    );
DESC supplyer;

CREATE TABLE IF NOT EXISTS stock(
    stockId VARCHAR(6) NOT NULL,
    supId VARCHAR(6),
    description VARCHAR(40),
    modelName VARCHAR(40),
    CONSTRAINT PRIMARY KEY(stockId),
    CONSTRAINT FOREIGN KEY(stockId) REFERENCES supplyer(supId)
    ON DELETE CASCADE ON UPDATE CASCADE
    );
DESC stock;

CREATE TABLE IF NOT EXISTS stockDetails(
    stockId VARCHAR(6),
    itemId VARCHAR(6),
    qty INT(15),
    modelName VARCHAR(30),
    CONSTRAINT PRIMARY KEY(stockId,itemId),
    CONSTRAINT FOREIGN KEY(stockId) REFERENCES stock(stockId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(itemId) REFERENCES item(itemId) ON DELETE CASCADE ON UPDATE CASCADE
    );
DESC stockDetails;

CREATE TABLE IF NOT EXISTS employees(
    empId VARCHAR(6) NOT NULL,
    cusId VARCHAR(6),
    empName VARCHAR(20),
    address VARCHAR(40),
    CONSTRAINT PRIMARY KEY(empId),
    CONSTRAINT FOREIGN KEY(empId) REFERENCES customer(cusId)
    ON DELETE CASCADE ON UPDATE CASCADE
    );
DESC employees;

CREATE TABLE IF NOT EXISTS servise(
    serviseId VARCHAR(6) NOT NULL,
    empId VARCHAR(6),
    name VARCHAR(20),
    CONSTRAINT PRIMARY KEY(serviseId),
    CONSTRAINT FOREIGN KEY(serviseId) REFERENCES employees(empId)
    ON DELETE CASCADE ON UPDATE CASCADE
    );
DESC servise;

CREATE TABLE IF NOT EXISTS incomeReport(
    incomeId VARCHAR(6) NOT NULL,
    serviseId VARCHAR(6),
    serviseBill DECIMAL(8,2),
    ordeRBill DECIMAL(8,2),
    CONSTRAINT PRIMARY KEY(incomeId),
    CONSTRAINT FOREIGN KEY(incomeId) REFERENCES servise(serviseId)
    );
DESC incomeReport;