create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table role (role_id integer not null, role varchar(255), primary key (role_id)) engine=InnoDB;
create table token (id integer not null, value varchar(255), primary key (id)) engine=InnoDB;
create table user (user_id integer not null, active integer, email varchar(255), last_name varchar(255), name varchar(255), password varchar(255), primary key (user_id)) engine=InnoDB;
create table user_role (user_id integer not null, role_id integer not null, primary key (user_id, role_id)) engine=InnoDB;
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (role_id);
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (user_id);