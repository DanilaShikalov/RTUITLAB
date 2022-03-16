create table IF NOT EXISTS unit
(
    id   integer auto_increment primary key,
    unit varchar
);

insert into unit (unit) values ( 'test' );