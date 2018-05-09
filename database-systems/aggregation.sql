drop table grade;

create table grade (name char(10), grade number);

insert into grade values ('amy', 65);
insert into grade values ('amy', 65);
insert into grade values ('amy', 72);
insert into grade values ('amy', 82);
insert into grade values ('amy', 85);
insert into grade values ('amy', 85);
insert into grade values ('amy', 90);
insert into grade values ('amy', 95);

insert into grade values ('bob', 85);
insert into grade values ('bob', 85);
insert into grade values ('bob', 85);
insert into grade values ('bob', 95);
insert into grade values ('bob', 95);
insert into grade values ('bob', 97);
insert into grade values ('bob', 99);


select avg(grade) from grade where name = 'amy';
/*
| AVG(GRADE) |
|------------|
|     79.875 |
*/

select avg(distinct grade) as grade from grade where name = 'amy';
/*
| GRADE |
|-------|
|  81.5 |
*/

select count(distinct grade) as count from grade where name = 'amy';
/*
| COUNT |
|-------|
|     6 |
*/

select count(*) as count from grade where name = 'amy';
/*
| COUNT |
|-------|
|     8 |
*/

select max(grade) from grade where name = 'amy';
/*
| MAX(GRADE) |
|------------|
|         95 |
*/

select min(grade) from grade where name = 'amy';
/*
| MIN(GRADE) |
|------------|
|         65 |
*/

select sum(grade) from grade where name = 'amy';
/*
| SUM(GRADE) |
|------------|
|        639 |
*/

select sum(distinct grade) from grade where name = 'amy';
/*
| SUM(DISTINCTGRADE) |
|--------------------|
|                489 |
*/

select name, avg(grade) as grade from grade group by name;
/*
|       NAME |             GRADE |
|------------|-------------------|
| amy        |            79.875 |
| bob        | 91.57142857142857 |
*/

select name, avg(grade) as grade from grade where grade <> 85 group by name;
/*
|       NAME |             GRADE |
|------------|-------------------|
| amy        | 78.16666666666667 |
| bob        |              96.5 |
*/

select name, avg(grade) as grade from grade where grade <> 85 group by name having max(grade) > 95;
/*
|       NAME | GRADE |
|------------|-------|
| bob        |  96.5 |
*/

select name, avg(grade) as grade from grade where grade <> 85 group by name having mod(max(grade), 2) = 1;
/*
|       NAME |             GRADE |
|------------|-------------------|
| amy        | 78.16666666666667 |
| bob        |              96.5 |
*/

select count(*) from grade where name = 'chen';
-- 0
select count(grade) from grade where name = 'chen';
-- 0
select max(grade) from grade where name = 'chen';
-- null
select min(grade) from grade where name = 'chen';
-- null
select avg(grade) from grade where name = 'chen';
-- null
select sum(grade) from grade where name = 'chen';
-- null

select * from grade where name = 'amy' and grade > 
(select min(grade) from grade where name = 'bob');
/*
|       NAME | GRADE |
|------------|-------|
| amy        |    90 |
| amy        |    95 |
*/

select * from grade grade1 where grade > 
(select avg(grade) from grade where name = grade.name);
/*
|       NAME | GRADE |
|------------|-------|
| amy        |    90 |
| amy        |    95 |
| bob        |    95 |
| bob        |    95 |
| bob        |    97 |
| bob        |    99 |
*/

