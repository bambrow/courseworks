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

select First from R where Second = 'a';
/*
| FIRST |
|-------|
|     b |
|     b |
|     b |
|     b |
|     b |
*/

select R.First from R, S where R.First = S.First and R.Second = S.Second;
/*
| FIRST |
|-------|
|     b |
|     b |
|     b |
|     b |
|     b |
|     b |
|     b |
|     b |
|     b |
|     b |
|     b |
*/

select distinct R.First, S.Second from R, S where R.Second = S.First;
/*
|  FIRST | SECOND |
|--------|--------|
|      a |      a |
| (null) |      b |
| (null) |      a |
|      b |      a |
|      a |      b |
|      a |      c |
|      b |      b |
*/

select distinct R.First, S.Second from R, S where R.Second = S.First and S.Second = 'b';
/*
|  FIRST | SECOND |
|--------|--------|
| (null) |      b |
|      a |      b |
|      b |      b |
*/

drop table R1;
create table R1 as select * from R;
select distinct R.First, R1.Second from R, R1 where R.Second = R1.First;
/*
| FIRST | SECOND |
|-------|--------|
|     a |      a |
|     b |      c |
|     a | (null) |
|     b |      b |
|     a |      c |
*/

drop table R2;
create table R2 as select * from R;
select distinct R.First, R2.Second from R, R1, R2 where R.Second = R1.First and R1.Second = R2.First;
/*
| FIRST | SECOND |
|-------|--------|
|     b | (null) |
|     b |      c |
|     b |      a |
|     a |      b |
|     a |      c |
*/

drop table R1;
drop table R2;

select distinct R.First, R1.Second from R, R R1 where R.Second = R1.First;
/*
| FIRST | SECOND |
|-------|--------|
|     a |      a |
|     b |      c |
|     a | (null) |
|     b |      b |
|     a |      c |
*/

select distinct R.First, R2.Second from R, R R1, R R2 where R.Second = R1.First and R1.Second = R2.First;
/*
| FIRST | SECOND |
|-------|--------|
|     b | (null) |
|     b |      c |
|     b |      a |
|     a |      b |
|     a |      c |
*/



