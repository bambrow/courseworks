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


select * from grade
where grade between 85 and 95;
/*
|       NAME | GRADE |
|------------|-------|
| amy        |    85 |
| amy        |    85 |
| amy        |    90 |
| amy        |    95 |
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
*/

select * from grade
where (grade between 85 and 95) and grade <> 95;
/*
|       NAME | GRADE |
|------------|-------|
| amy        |    85 |
| amy        |    85 |
| amy        |    90 |
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
*/

select name, grade/100 as percentage from grade
where (grade between 85 and 95) and grade <> 95;
/*
|       NAME | PERCENTAGE |
|------------|------------|
| amy        |       0.85 |
| amy        |       0.85 |
| amy        |        0.9 |
| bob        |       0.85 |
| bob        |       0.85 |
| bob        |       0.85 |
*/

select name, grade/100 as percentage from grade
where grade/100 like '_8%';
/*
|       NAME | PERCENTAGE |
|------------|------------|
| amy        |       0.82 |
| amy        |       0.85 |
| amy        |       0.85 |
| bob        |       0.85 |
| bob        |       0.85 |
| bob        |       0.85 |
*/
-- '_8%' stands for 8 in the second position
-- _ stands for exactly one character
-- % stands for zero or more characters

select name, grade/100 as percentage from grade
where (grade between 85 and 95) and grade <> 95
order by name desc, percentage asc;
/*
|       NAME | PERCENTAGE |
|------------|------------|
| bob        |       0.85 |
| bob        |       0.85 |
| bob        |       0.85 |
| amy        |       0.85 |
| amy        |       0.85 |
| amy        |        0.9 |
*/

select name, grade/100 as percentage from grade
where (grade between 85 and 95) and grade <> 95
order by name asc, percentage desc;
/*
|       NAME | PERCENTAGE |
|------------|------------|
| amy        |        0.9 |
| amy        |       0.85 |
| amy        |       0.85 |
| bob        |       0.85 |
| bob        |       0.85 |
| bob        |       0.85 |
*/



