drop table arc;

create table arc (head number, tail number, primary key (head, tail));

insert into arc values (1, 3);
insert into arc values (2, 4);
insert into arc values (3, 4);
insert into arc values (4, 6);
insert into arc values (5, 8);
insert into arc values (8, 9);


with route (head, tail) as
(  
  select a.head, a.tail from arc a
  union all
  select a.head, r.tail from route r
  inner join arc a
  on a.tail = r.head
)

select * from route;

/*
| HEAD | TAIL |
|------|------|
|    1 |    3 |
|    2 |    4 |
|    3 |    4 |
|    4 |    6 |
|    5 |    8 |
|    8 |    9 |
|    1 |    4 |
|    2 |    6 |
|    3 |    6 |
|    5 |    9 |
|    1 |    6 |
*/


