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


select * from grade;
/*
|       NAME | GRADE |
|------------|-------|
| amy        |    65 |
| amy        |    65 |
| amy        |    72 |
| amy        |    82 |
| amy        |    85 |
| amy        |    85 |
| amy        |    90 |
| amy        |    95 |
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
| bob        |    97 |
| bob        |    99 |
*/

drop table temp;
create table temp (name char(10), grade char(10), complete char(10));
insert into temp values('chen', 89, 'YES');
insert into temp values('chen', 92, 'NO');
insert into temp values('chen', 99, 'YES');
insert into temp values('chen', 97, 'NO');

select * from temp;
/*
|       NAME |      GRADE |   COMPLETE |
|------------|------------|------------|
| chen       | 89         | YES        |
| chen       | 92         | NO         |
| chen       | 99         | YES        |
| chen       | 97         | NO         |
*/

insert into grade (name, grade)
select name, grade from temp
where complete = 'YES';

select * from grade;
/*
|       NAME | GRADE |
|------------|-------|
| chen       |    89 |
| chen       |    99 |
| amy        |    65 |
| amy        |    65 |
| amy        |    72 |
| amy        |    82 |
| amy        |    85 |
| amy        |    85 |
| amy        |    90 |
| amy        |    95 |
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
| bob        |    97 |
| bob        |    99 |
*/

delete from temp where complete = 'YES';

select * from temp;
/*
|       NAME |      GRADE |   COMPLETE |
|------------|------------|------------|
| chen       | 92         | NO         |
| chen       | 97         | NO         |
*/

update temp set complete = 'YES' where grade > 90;

select * from temp;
/*
|       NAME |      GRADE |   COMPLETE |
|------------|------------|------------|
| chen       | 92         | YES        |
| chen       | 97         | YES        |
*/

insert into grade (name, grade)
select name, grade from temp
where complete = 'YES';

select * from grade;
/*
|       NAME | GRADE |
|------------|-------|
| chen       |    89 |
| chen       |    99 |
| chen       |    92 |
| chen       |    97 |
| amy        |    65 |
| amy        |    65 |
| amy        |    72 |
| amy        |    82 |
| amy        |    85 |
| amy        |    85 |
| amy        |    90 |
| amy        |    95 |
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
| bob        |    97 |
| bob        |    99 |
*/

delete from grade where exists
(select name, grade from temp 
 where name = grade.name and grade = grade.grade);

select * from grade;
/*
|       NAME | GRADE |
|------------|-------|
| chen       |    89 |
| chen       |    99 |
| amy        |    65 |
| amy        |    65 |
| amy        |    72 |
| amy        |    82 |
| amy        |    85 |
| amy        |    85 |
| amy        |    90 |
| amy        |    95 |
| bob        |    85 |
| bob        |    85 |
| bob        |    85 |
| bob        |    95 |
| bob        |    95 |
| bob        |    97 |
| bob        |    99 |
*/
