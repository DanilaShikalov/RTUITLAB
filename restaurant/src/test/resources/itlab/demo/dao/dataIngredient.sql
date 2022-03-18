create table IF NOT EXISTS ingredient
(
    id           integer auto_increment primary key,
    id_menu      integer,
    id_warehouse integer
);

insert into ingredient (id_menu, id_warehouse) VALUES ( 1, 1 );