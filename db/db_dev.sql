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

INSERT INTO public.student (name, passport) VALUES ('Vasa', '123');
INSERT INTO public.student (name, passport) VALUES ( 'Ivan', '345');
INSERT INTO public.student (name, passport) VALUES ('Boba', 'kdfpogjer9oghodfg');
INSERT INTO public.student (name, passport) VALUES ('Zeliboba', '555');



