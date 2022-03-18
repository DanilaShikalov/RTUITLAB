create table IF NOT EXISTS menu
(
    id      integer auto_increment primary key,
    id_unit integer,
    title   varchar,
    price   integer,
    weight  integer
);

insert into menu (id_unit, title, price, weight) VALUES ( 1, 'pizza', 350, 500 );