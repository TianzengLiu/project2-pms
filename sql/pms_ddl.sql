set schema 'pms';

--drop table roles;

create table roles(
role_id serial primary key,
role_name text unique not null
);

--drop table permits;

create table permits(
permit_id serial primary key,
parking_spot text not null,
vehicle_license text not null,
initial_date bigint not null,
expiry_date bigint not null
);

--drop table users;

create table users(
user_id serial primary key,
username text unique not null,
passwd text not null,
first_name text not null,
last_name text not null,
email text not null,
address text not null,
role_id integer references roles (role_id) not null,
permit_id integer references permits (permit_id)
);
