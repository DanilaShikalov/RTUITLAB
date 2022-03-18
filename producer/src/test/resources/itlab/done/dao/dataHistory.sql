create table IF NOT EXISTS history
(
    id            integer auto_increment primary key,
    id_product    integer,
    amount        integer,
    date_delivery date
);

insert into history (id_product, amount, date_delivery) VALUES ( 1, 3, '2020-10-10' );