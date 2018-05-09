drop table r;
drop table s;

create table r (a char(10), b char(10));
create table s (c char(10), d char(10));

insert into r values ('a', '1');
insert into r values ('b', '2');
insert into r values ('c', '3');
insert into s values ('1', 'e');
insert into s values ('2', 'f');
insert into s values ('2', 'g');
insert into s values ('4', 'h');


select * 
from r left outer join s
on r.b = s.c;
/*
|          A |          B |          C |          D |
|------------|------------|------------|------------|
| a          | 1          | 1          | e          |
| b          | 2          | 2          | f          |
| b          | 2          | 2          | g          |
| c          | 3          |     (null) |     (null) |
*/
-- includes all rows from the first table
-- matched or not
-- and matched rows from the second table

select * 
from r right outer join s
on r.b = s.c;
/*
|          A |          B |          C |          D |
|------------|------------|------------|------------|
| a          | 1          | 1          | e          |
| b          | 2          | 2          | g          |
| b          | 2          | 2          | f          |
|     (null) |     (null) | 4          | h          |
*/
-- includes all rows from the second table
-- matched or not
-- and matched rows from the first table

select * 
from r full outer join s
on r.b = s.c;
/*
|          A |          B |          C |          D |
|------------|------------|------------|------------|
| a          | 1          | 1          | e          |
| b          | 2          | 2          | f          |
| b          | 2          | 2          | g          |
|     (null) |     (null) | 4          | h          |
| c          | 3          |     (null) |     (null) |
*/
-- includes all rows from both tables

select * 
from r inner join s
on r.b = s.c;
/*
|          A |          B |          C |          D |
|------------|------------|------------|------------|
| a          | 1          | 1          | e          |
| b          | 2          | 2          | f          |
| b          | 2          | 2          | g          |
*/

select * 
from r join s
on r.b = s.c;
/*
|          A |          B |          C |          D |
|------------|------------|------------|------------|
| a          | 1          | 1          | e          |
| b          | 2          | 2          | f          |
| b          | 2          | 2          | g          |
*/

-- the following gives an error
/*
select * 
from r outer join s
on r.b = s.c;
*/

select * 
from r natural join s;
/*
|          A |          B |          C |          D |
|------------|------------|------------|------------|
| a          | 1          | 1          | e          |
| a          | 1          | 2          | f          |
| a          | 1          | 2          | g          |
| a          | 1          | 4          | h          |
| b          | 2          | 1          | e          |
| b          | 2          | 2          | f          |
| b          | 2          | 2          | g          |
| b          | 2          | 4          | h          |
| c          | 3          | 1          | e          |
| c          | 3          | 2          | f          |
| c          | 3          | 2          | g          |
| c          | 3          | 4          | h          |
*/

drop table t;
create table t as
select c as b, d as c from s;
select * from t;
/*
|          B |          C |
|------------|------------|
| 1          | e          |
| 2          | f          |
| 2          | g          |
| 4          | h          |
*/
select * from r natural join t;
/*
|          B |          A |          C |
|------------|------------|------------|
| 1          | a          | e          |
| 2          | b          | f          |
| 2          | b          | g          |
*/
-- natural join works as follows
-- select one copy of each column name
-- from r1, r2, ..., rn
-- where equal-named columns must be equal
-- it will automatically match equal name columns
-- and do the joins accordingly
