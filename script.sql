/*
 * Parse do script MySQL para SQLite
 */

DROP TABLE IF EXISTS DEPARTMENT;

-- CREATE TABLE SYNTAX
CREATE TABLE DEPARTMENT(
	ID_DEPARTMENT INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	NAME VARCHAR(60) NOT NULL
);

-- INSERT SYNTAX
INSERT INTO DEPARTMENT('NAME')VALUES
('Computers'),
('Electronics'),
('Fashion'),
('Books');

-- SELECT SYNTAX
SELECT * FROM DEPARTMENT;

DROP TABLE IF EXISTS SELLER;

CREATE TABLE SELLER(
	ID INTEGER PRIMARY KEY AUTOINCREMENT,
	NAME VARCHAR(60) NOT NULL,
	EMAIL VARCHAR(100) NOT NULL,
--	BIRTHDATE DATETIME(6) NOT NULL,
	BIRTHDATE TEXT(6) NOT NULL,
	BASESALARY DOUBLE NOT NULL,
	ID_DEPARTMENT INTEGER NOT NULL,
	FOREIGN KEY (ID_DEPARTMENT) REFERENCES DEPARTMENT (ID_DEPARTMENT) ON DELETE RESTRICT
);

DELETE FROM SELLER WHERE ID = 6;

DELETE FROM DEPARTMENT WHERE ID_DEPARTMENT = 2;


INSERT INTO SELLER ('NAME', 'EMAIL', 'BIRTHDATE', 'BASESALARY', 'ID_DEPARTMENT')VALUES 
('Bob Brown','bob@gmail.com','1998-04-21 00:00:00.000000',1000,1),
('Maria Green','maria@gmail.com','1979-12-31 00:00:00.000000',3500,2),
('Alex Grey','alex@gmail.com','1988-01-15 00:00:00.000000',2200,1),
('Martha Red','martha@gmail.com','1993-11-30 00:00:00.000000',3000,4),
('Donald Blue','donald@gmail.com','2000-01-09 00:00:00.000000',4000,3),
('Alex Pink','bob@gmail.com','1997-03-04 00:00:00.000000',3000,2);

SELECT * FROM SELLER;

-- DESCRIBE TABLE
SELECT * FROM sqlite_master WHERE name = 'SELLER';