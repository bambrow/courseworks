drop table took;
drop table required;

create table took (person char(10), course char(10));
create table required (course char(10));

insert into took values ('marsha', 'os');
insert into took values ('marsha', 'db');
insert into took values ('marsha', 'ai');
insert into took values ('vijay', 'os');
insert into took values ('vijay', 'db');
insert into took values ('dong', 'os');
insert into took values ('dong', 'ai');
insert into took values ('chris', 'ai');
insert into took values ('chris', 'en');
insert into required values ('os');
insert into required values ('db');


-- asking about some
-- person who took some courses that are required
select distinct person from took, required where took.course = required.course;
/*
|     PERSON |
|------------|
| dong       |
| marsha     |
| vijay      |
*/

-- asking about none
-- person who took no required courses
-- WRONG EXAMPLE
select distinct person from took, required where took.course <> required.course;
/*
|     PERSON |
|------------|
| dong       |
| marsha     |
| vijay      |
| chris      |
*/

-- RIGHT ANSWER
select person from took minus select person from took, required where took.course = required.course;
/*
|     PERSON |
|------------|
| chris      |
*/


-- asking about all
-- person who took all required courses
drop table temp1;
drop table temp2;
drop table temp3;
drop table temp4;
create table temp1 as select person from took;
create table temp2 as select person, course from temp1, required;
create table temp3 as select * from temp2 minus select * from took;
create table temp4 as select person from temp3;
select * from temp1 minus select * from temp4;
/*
|     PERSON |
|------------|
| marsha     |
| vijay      |
*/

-- or we can avoid creating new tables
select person from took minus 
(
  select person from 
  (
    select person, required.course from took, required
    minus
    select * from took
  )
);
/*
|     PERSON |
|------------|
| marsha     |
| vijay      |
*/

