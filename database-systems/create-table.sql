-- basic definition

drop table plant;
drop table customer;
drop table invoice;

create table plant (
	p char(10),
	pname char varying(10),
	pcity char varying(10),
	profit number
);

create table customer (
	c char(10),
	cname char varying(10),
	ccity char varying(10),
	p char(10)
);

create table invoice (
	i char(10),
	amt number,
	idate date,
	c char(10)
);


-- more extensive

drop table plant;
drop table customer;
drop table invoice;

create table plant (
	p char(10) not null,
	pname char varying(10),
	pcity char varying(10),
	profit number
);

create table customer (
	c char(10) not null,
	cname char varying(10) default(null),
	ccity char varying(10),
	p char(10) default('main')
);

create table invoice (
	i char(10) not null,
	amt number,
	idate date,
	c char(10) not null
);


-- add constraints

drop table plant;
drop table customer;
drop table invoice;

create table plant (
	p char(10) not null,
	pname char varying(10),
	pcity char varying(10),
	profit number,
	constraint c20 primary key (p),
	constraint c30 unique (pcity, profit),
	constraint c40 check (pcity <> pname),
	constraint c50 check ((pcity <> 'chicago') or (profit > 1000))
);

create table customer (
	c char(10) not null,
	cname char varying(10) default(null),
	ccity char varying(10),
	p char(10) default('main'),
	constraint c60 primary key (c),
	constraint c70 foreign key (p) references plant(p) on delete set null
);

create table invoice (
	i char(10) not null,
	amt number,
	idate date,
	c char(10) not null,
	constraint c80 primary key (i),
	constraint c90 foreign key (c) references customer(c) on delete cascade
);


-- we can also do this

drop table plant;
drop table customer;
drop table invoice;

create table plant (
	p char(10) not null,
	pname char varying(10),
	pcity char varying(10),
	profit number
);

create table customer (
	c char(10) not null,
	cname char varying(10) default(null),
	ccity char varying(10),
	p char(10) default('main')
);

create table invoice (
	i char(10) not null,
	amt number,
	idate date,
	c char(10) not null
);

alter table plant add constraint c20 primary key (p);
alter table customer add constraint c60 primary key (c);
alter table invoice add constraint c80 primary key (i);
alter table customer add constraint c70 foreign key (p) references plant(p) on delete set null;
alter table invoice add constraint c90 foreign key (c) references customer(c) on delete cascade;

alter table plant add constraint c30 unique (pcity, profit);
alter table plant add constraint c40 check (pcity <> pname);
alter table plant add constraint c50 check ((pcity <> 'chicago') or (profit > 1000));

-- foreign key can refer to unique, not only to primary key

-- another example

drop table woman;

create table woman (
	ssn char(9) not null,
	name char varying(10) default (null),
	mother char(9) default (null)
);

alter table woman add constraint c01 primary key (ssn);
alter table woman add constraint c02 foreign key (mother) references woman(ssn);


-- auto increment

drop table plant;
drop table customer;
drop table invoice;

create table plant (
	plant_id number generated always as identity,
	p char(10) not null,
	pname char varying(10),
	pcity char varying(10),
	profit number,
	primary key (plant_id),
	unique (p)
);

