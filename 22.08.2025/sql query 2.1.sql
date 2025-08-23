CREATE DATABASE ORG123;
SHOW DATABASES;
USE ORG123;

CREATE TABLE Worker (
	WORKER_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	FIRST_NAME CHAR(25),
	LAST_NAME CHAR(25),
	SALARY INT(15),
	JOINING_DATE DATETIME,
	DEPARTMENT CHAR(25)
);

INSERT INTO Worker 
  (WORKER_ID, FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT) VALUES
   (1, 'Monika', 'Arora', 100000, '2014-02-20 09:00:00', 'HR'),
   (2, 'Niharika', 'Verma', 80000, '2014-06-11 09:00:00', 'Admin'),
   (3, 'Vishal', 'Singhal', 300000, '2014-02-20 09:00:00', 'HR'),
   (4, 'Amitabh', 'Singh', 500000, '2014-02-20 09:00:00', 'Admin'),
   (5, 'Vivek', 'Bhati', 500000, '2014-06-11 09:00:00', 'Admin'),
   (6, 'Vipul', 'Diwan', 200000, '2014-06-11 09:00:00', 'Account'),
   (7, 'Satish', 'Kumar', 75000, '2014-01-20 09:00:00', 'Account'),
   (8, 'Geetika', 'Chauhan', 90000, '2014-04-11 09:00:00', 'Admin');

select * from worker;
delete from worker where worker_id=2;
delete from worker where worker_id=4;
truncate table worker;
start transaction;
delete from worker where worker_id=6;
savepoint A;
rollback to A;
desc worker;



