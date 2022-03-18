create table IF NOT EXISTS product
(
    id     integer auto_increment primary key,
    title  varchar,
    weight integer,
    amount integer
);

insert into product (title, weight, amount) VALUES ( 'test', 500, 3 );