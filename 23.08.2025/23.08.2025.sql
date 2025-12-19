create database TCS;
show databases;
use TCS;

create table Employee (
   emp_id int not null unique auto_increment,   
   emp_LastName varchar(255) not null,          
   emp_FirstName varchar(255) not null,         
   emp_age int not null,                       
   check (emp_age>=25),
   emp_salary int not null 
);

INSERT INTO employee (emp_LastName, emp_FirstName, emp_age, emp_salary)
VALUES 
('s','Aditya',30,3000),
('rovan','akash',25,27000),
('A','Acelin',27,45000);

SELECT * FROM Employee ;
desc employee;

ALTER TABLE Employee
DROP INDEX emp_id,            
ADD PRIMARY KEY (emp_id);    

alter table employee add emp_email int;

ALTER TABLE Employee MODIFY emp_email VARCHAR(30);

desc employee;
SELECT * FROM Employee ;

SELECT * FROM Employee WHERE emp_id = 1;
