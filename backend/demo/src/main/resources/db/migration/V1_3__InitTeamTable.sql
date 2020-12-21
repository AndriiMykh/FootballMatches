create table team(
	id integer primary key,
	name varchar(40),
	country varchar(30),
	imgURL varchar(100));
alter table event 
add column host integer references team(id), 
add column guest integer references team(id);