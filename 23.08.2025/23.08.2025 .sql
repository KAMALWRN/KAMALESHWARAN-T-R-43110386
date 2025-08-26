create database amazon ;
show databases ;
use amazon ;
 create table category (
      cid int primary key ,
      cname varchar(50) not null
);

insert into category values (1001,'electronics');
insert into category values (1002,'furniture');
insert into category values (1003,'dress');

select * from category ;

create table product (
       pid int primary key,
       pname varchar(50) not null,
       cid int not null,
       foreign key (cid) references category(cid) 
);

insert into product values (901,'sonata smart watch',1003);
insert into product values (902,'iphone 16',1001);
insert into product values (903,'iqoo z10',1001);

select * from product ;

delete from category where cid=1001;

update category set cid=1003 where cid=1001;


