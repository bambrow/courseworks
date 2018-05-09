drop table R;
drop table S;
create table R (First char varying(10), Second char varying(10));
create table S (First char varying(10), Second char varying(10));

insert into R values ('a', 'b');
insert into R values ('a', 'c');
insert into R values ('b', 'a');
insert into R values ('b', 'a');
insert into R values ('b', 'a');
insert into R values ('b', 'a');
insert into R values ('b', 'a');
insert into R values ('b', 'c');
insert into R values ('b', null);
insert into R values (null, 'c');
insert into R values (null, 'c');

insert into S values ('b', 'a');
insert into S values ('b', 'a');
insert into S values ('b', 'c');
insert into S values ('c', 'a');
insert into S values ('c', 'b');
insert into S values (null, 'c');


select * from R;
/*
|  FIRST | SECOND |
|--------|--------|
|      a |      b |
|      a |      c |
|      b |      a |
|      b |      a |
|      b |      a |
|      b |      a |
|      b |      a |
|      b |      c |
|      b | (null) |
| (null) |      c |
| (null) |      c |
*/

select * from S;
/*
|  FIRST | SECOND |
|--------|--------|
|      b |      a |
|      b |      a |
|      b |      c |
|      c |      a |
|      c |      b |
| (null) |      c |
*/

select distinct * from R;
/*
|  FIRST | SECOND |
|--------|--------|
|      b | (null) |
|      b |      c |
|      b |      a |
| (null) |      c |
|      a |      b |
|      a |      c |
*/

select distinct * from S;
/*
|  FIRST | SECOND |
|--------|--------|
|      c |      b |
|      b |      c |
|      b |      a |
| (null) |      c |
|      c |      a |
*/

select * from R union select * from S;
/*
|  FIRST | SECOND |
|--------|--------|
|      a |      b |
|      a |      c |
|      b |      a |
|      b |      c |
|      b | (null) |
|      c |      a |
|      c |      b |
| (null) |      c |
*/

select * from R minus select * from S;
/*
| FIRST | SECOND |
|-------|--------|
|     a |      b |
|     a |      c |
|     b | (null) |
*/

select * from R intersect select * from S;
/*
|  FIRST | SECOND |
|--------|--------|
|      b |      a |
|      b |      c |
| (null) |      c |
*/

select * from R union all select * from S;
/*
|  FIRST | SECOND |
|--------|--------|
|      a |      b |
|      a |      c |
|      b |      a |
|      b |      a |
|      b |      a |
|      b |      a |
|      b |      a |
|      b |      c |
|      b | (null) |
| (null) |      c |
| (null) |      c |
|      b |      a |
|      b |      a |
|      b |      c |
|      c |      a |
|      c |      b |
| (null) |      c |
*/

-- select * from R minus all select * from S;
-- this does not work

-- select * from R intersect all select * from S;
-- this does not work

select * from R where First = null;
-- nothing

select * from R where First <> null;
-- nothing

select * from R where First = First;
/*
| FIRST | SECOND |
|-------|--------|
|     a |      b |
|     a |      c |
|     b |      a |
|     b |      a |
|     b |      a |
|     b |      a |
|     b |      a |
|     b |      c |
|     b | (null) |
*/

select * from R where First <> First;
-- nothing

select * from R where First is null;
/*
|  FIRST | SECOND |
|--------|--------|
| (null) |      c |
| (null) |      c |
*/

select * from R where First is not null;
/*
| FIRST | SECOND |
|-------|--------|
|     a |      b |
|     a |      c |
|     b |      a |
|     b |      a |
|     b |      a |
|     b |      a |
|     b |      a |
|     b |      c |
|     b | (null) |
*/

select distinct * from R
where exists
(select * from S where R.First = S.First and R.Second = S.Second);
/*
| FIRST | SECOND |
|-------|--------|
|     b |      c |
|     b |      a |
*/
-- intersect using exists
-- this removes all null while intersect does not

select distinct * from R
where not exists
(select * from S where R.First = S.First and R.Second = S.Second);
/*
|  FIRST | SECOND |
|--------|--------|
|      b | (null) |
| (null) |      c |
|      a |      b |
|      a |      c |
*/
-- minus using not exists
-- this retains all null while minus does not

