create table person(
	id integer primary key,
	email varchar(40),
	name varchar(30),
	password varchar(30),
	phone_number varchar(15));
	
create table event(
	id integer primary key,
	place varchar(50),
	time timestamp
);
create table person_events(
	person_id integer references person(id),
	event_id integer references event(id)
);