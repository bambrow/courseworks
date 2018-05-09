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


select * from grade where name = 'bob' and grade > all
(select grade from grade where name = 'amy');
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    97 |
| bob        |    99 |
*/

select * from grade where name = 'amy' and grade > any
(select grade from grade where name = 'bob');
/*
|       NAME | GRADE |
|------------|-------|
| amy        |    95 |
| amy        |    90 |
*/

select * from grade where name = 'bob' and grade = any
(select grade from grade where name = 'amy');
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
*/

select * from grade where name = 'bob' and grade <> any
(select grade from grade where name = 'amy');
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
| bob        |    97 |
| bob        |    99 |
*/

select * from grade where name = 'bob' and grade <> all
(select grade from grade where name = 'amy');
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    99 |
| bob        |    97 |
*/

select * from grade where name = 'bob' and grade = all
(select grade from grade where name = 'amy');
-- nothing

select * from grade grade1 where exists
(select grade from grade where name = 'amy' and grade1.name = 'bob' and grade = grade1.grade);
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
*/

select * from grade grade1 where name = 'bob' and exists
(select grade from grade where name = 'amy' and grade = grade1.grade);
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
*/

select * from grade grade1 where name = 'bob' and not exists
(select grade from grade where name = 'amy' and grade = grade1.grade);
/*
|       NAME | GRADE |
|------------|-------|
| bob        |    99 |
| bob        |    97 |
*/


