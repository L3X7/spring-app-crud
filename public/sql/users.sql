create  table users(
	user_id serial primary key,
	username varchar(50) unique not null,
	password varchar(50) not null,
	email varchar(50) not null,
	created_on timestamp not null,
	modified_on timestamp		
)