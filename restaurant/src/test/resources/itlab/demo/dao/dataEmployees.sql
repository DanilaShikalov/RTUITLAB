create table IF NOT EXISTS employees
(
    id          integer auto_increment primary key,
    id_position integer,
    name        varchar,
    surname     varchar,
    second_name varchar,
    number      varchar
);

insert into employees (id_position, name, surname, second_name, number) VALUES ( 1, 'Danila', 'Shikalov',
                                                                                'Denisovich', '88005553535');