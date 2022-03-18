create table IF NOT EXISTS dish
(
    id        integer auto_increment primary key,
    id_orders integer,
    id_menu   integer
);

insert into dish (id_orders, id_menu) VALUES ( 1, 1 );