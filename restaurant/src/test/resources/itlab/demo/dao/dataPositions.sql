create table IF NOT EXISTS positions
(
    id       integer auto_increment primary key,
    position varchar,
    salary   integer
);

create table IF NOT EXISTS employees
(
    id          integer auto_increment primary key,
    id_position integer,
    name        varchar,
    surname     varchar,
    second_name varchar,
    number      varchar
);

insert into positions (position, salary) values ( 'test', 10000 );
insert into employees (id_position, name, surname, second_name, number) values ( 1, 'test', 'test', 'test', 'test' );