drop trigger r1;
drop trigger r2;
drop table t1;
drop table t2;
drop view v1;
drop view v2;

create table t1 (a char(10) not null, b char(10) not null unique, primary key (a));
create table t2 (a char(10) not null, c char(10) not null unique, primary key (a));
insert into t1 values ('1', '2');
insert into t1 values ('2', '4');
insert into t1 values ('3', '6');
insert into t2 values ('1', '11');
insert into t2 values ('2', '22');
insert into t2 values ('3', '33');

create view v1 as select t1.a as a, t1.b as b, t2.c as c from t1, t2 where t1.a = t2.a;

create trigger r1 
instead of update on v1
referencing NEW as new OLD as old
begin 
update t2 
set c = :new.c 
where a = :old.a; 
end r1;
/ 
-- this can be created, but t2 can actually get updated without it when updating v1

select * from v1;
/*
A	   B	      C
---------- ---------- ----------
1	   2	      11
2	   4	      22
3	   6	      33
*/

update v1 set v1.c = '111' where v1.a = '1';

select * from v1;
/*
A	   B	      C
---------- ---------- ----------
1	   2	      111
2	   4	      22
3	   6	      33
*/


create view v2 as select t1.b as b, t2.c as c from t1, t2 where t1.a = t2.a;

create trigger r2
instead of update on v2
referencing NEW as new OLD as old
begin
update t2 set c = :new.c
where exists (select * from t1 where t2.a = t1.a and t1.b = :old.b);
end r2;
/


select * from v2;
/*
B	   C
---------- ----------
2	   111
4	   22
6	   33
*/

update v2 set v2.c = '222' where v2.b = '4';

/*
B	   C
---------- ----------
2	   111
4	   222
6	   33
*/

select * from t2;
/*
A	   C
---------- ----------
1	   111
2	   222
3	   33
*/


