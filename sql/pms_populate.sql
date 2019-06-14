set schema 'pms';

insert into roles values 
(default, 'manager'),
(default, 'user');

select * from roles;

insert into users values
(default, 'manager', 'password', 'Super', 'User', 'manager@organization.org', '1234 Address Street', 1, null),
(default, 'user', 'password', 'Normal', 'User', 'user@organization.org', '4321 Address Street', 2, null);

select * from users;

insert into permits values
(default, 'A1', 'ABC1234', 1560522364903, 1560522364904),
(default, 'B1', 'ABC4321', 1560522364903, 1560522364904);

select * from permits;