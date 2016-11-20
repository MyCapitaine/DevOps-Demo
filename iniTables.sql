start transaction;
drop schema devopsdemo;
create schema devopsdemo;
use devopsdemo;

create table users(
    uname varchar(20) unique
);

insert into users value('check');

create table items(
    iid int(10) primary key auto_increment,
    iname varchar(20) unique,
    iInfo varchar(100),
    possibility varchar(5),
    influence varchar(5),
    tri varchar(5),
    submitUser varchar(20),
    riskState varchar(5),
    data1 int(5),
    data2 int(5)
);

create table problems(
    iid int(10),
    birthTime dateTime
);

create table groups(
    gid int(10) primary key auto_increment,
    gname varchar(20) unique
);

create table itemwithgroup(
    gid int(10),
    iid int(10)
);
