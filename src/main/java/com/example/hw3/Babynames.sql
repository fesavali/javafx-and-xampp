drop database if exists babynames;
create database babynames;

use babynames;

drop table if exists babyname;

create table babyname(
  byear integer,
  bname varchar(50),
  gender char(1),
  bcount integer,
  constraint pkBabyname primary key (byear, bname, gender)
);

