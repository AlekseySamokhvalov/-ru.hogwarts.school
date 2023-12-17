create table car (
	id int primary key,
	mark text,
	model text,
	price int
);

create table person (
	id int primary key,
	name text,
	age int,
	have_license bool,
	car_id int,
	foreign key (car_id) references car(id)
);