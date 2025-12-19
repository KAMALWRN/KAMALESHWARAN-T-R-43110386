show databases ;
create database sathyabama ;
use sathyabama ;
create table ECE (
sid int,
sname varchar(30),
smarks int 
);
INSERT INTO ECE (sid, sname, smarks)
VALUES (1, 'Arun', 85),
       (2, 'Ben', 80),
       (3, 'Gopi', 78);
create table CSE (
sid int,
sname varchar(30),
smarks int
);
INSERT INTO CSE (sid, sname, smarks)
VALUES (1, 'Arun', 85),
       (2, 'Priya', 90),
       (3, 'gopi', 78);
show tables from sathyabama ;

desc CSE ;
desc ECE ;

select*from cse ;
select*from ece ;

show databases ;

alter table cse
add mailID varchar(100) ;

update CSE 
set mailID='arun2004@gmail.com'
where sid=1;

update CSE 
set mailID='priya2004@gmail.com'
where sid=2;

update CSE 
set mailID='gopi2004@gmail.com'
where sid=3;

select*from cse ;

alter table ece
add mailID varchar(100) ;

update ECE 
set mailID='arun2005@gmail.com'
where sid=1;

update ECE 
set mailID='ben2004@gmail.com'
where sid=2;

update ECE 
set mailID='gopi2004@gmail.com'
where sid=3;

select*from ece ;

