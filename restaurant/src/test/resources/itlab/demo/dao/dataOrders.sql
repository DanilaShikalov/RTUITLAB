create table IF NOT EXISTS orders
(
    id           integer auto_increment primary key,
    id_employees integer,
    date_open    date,
    status       boolean,
    date_close   date
);

insert into orders (id_employees, date_open, status, date_close) VALUES ( 1, '2022-10-10', true, '2020-10-10' );

