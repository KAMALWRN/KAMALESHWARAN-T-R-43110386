CREATE DATABASE KC;
SHOW DATABASES;
USE KC;

CREATE TABLE Worker (
    WORKER_ID INT PRIMARY KEY,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    DEPARTMENT VARCHAR(50) NOT NULL,
    SALARY DECIMAL(10,2) NOT NULL
);

INSERT INTO Worker VALUES
(1, 'Anu', 'Pallavi', 'HR', 95000.00),
(2, 'Shreyas', 'Rahul', 'Finance', 105000.00),
(3, 'Aniket', 'Verma', 'IT', 97000.00),
(4, 'Sundar', 'Pichai', 'Marketing', 102000.00),
(5, 'Lakshmi', 'Rai', 'HR', 79000.00),
(6, 'Leo', 'Das', 'IT', 108000.00),
(7, 'Vignesh', 'Kanna', 'Admin', 88000.00),
(8, 'Gopi', 'Krishna', 'Finance', 110000.00);

select * from worker;

select * from worker 
where department='hr' 
order by salary asc;

select count(*) as employee_count from worker 
where department not in('Hr','admin');

select department,max(salary) as highest_salary from worker 
group by department;

SELECT * FROM Worker 
ORDER BY WORKER_ID 
Limit 2 ;

SELECT * FROM Worker 
ORDER BY WORKER_ID 
Limit 2 offset 4;

SELECT * FROM ( 
SELECT *, ROW_NUMBER() OVER (ORDER BY WORKER_ID) AS rn 
FROM Worker 
) AS temp 
WHERE rn > 5;