DROP TABLE if exists student;
DROP SEQUENCE IF EXISTS student_id_seq;

create sequence student_id_seq;
--Student table
create table student
(
	id serial not null
		constraint student_pk
			primary key,
	name varchar,
	passport varchar not null
);

create unique index student_passport_uindex
	on student (passport);


