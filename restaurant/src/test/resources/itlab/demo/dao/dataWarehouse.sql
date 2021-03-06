create table IF NOT EXISTS warehouse
(
    id         integer auto_increment primary key,
    id_unit    integer,
    id_product integer,
    title      varchar,
    weight     integer,
    amount     integer,
    price      integer
);

create table IF NOT EXISTS unit
(
    id   integer auto_increment primary key,
    unit varchar
);

create table IF NOT EXISTS ingredient
(
    id           integer auto_increment primary key,
    id_menu      integer,
    id_warehouse integer
);

insert into warehouse (id_unit, id_product, title, weight, amount, price) values ( 1, 1, 'test', 1, 2, 3 );
insert into unit (unit) values ( 'test' );
insert into ingredient(id_menu, id_warehouse) VALUES ( 1, 1 );