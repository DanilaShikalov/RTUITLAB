create table positions
(
    id       serial primary key,
    position varchar,
    salary   integer
);

create table employees
(
    id          serial primary key,
    id_position integer,
    name        varchar,
    surname     varchar,
    second_name varchar,
    number      varchar
);

create table orders
(
    id           serial primary key,
    id_employees integer,
    date_open    date,
    status       boolean,
    date_close   date
);

create table dish
(
    id        serial primary key,
    id_orders integer,
    id_menu   integer
);

create table menu
(
    id      serial primary key,
    id_unit integer,
    title   varchar,
    price   integer,
    weight  integer
);

create table warehouse
(
    id         serial primary key,
    id_unit    integer,
    id_product integer,
    title      varchar,
    weight     integer,
    amount     integer,
    price      integer
);

create table unit
(
    id   serial primary key,
    unit varchar
);

create table ingredient
(
    id           serial primary key,
    id_menu      integer,
    id_warehouse integer
);

create table product
(
    id     serial primary key,
    title  varchar,
    weight integer,
    amount integer
);

create table history
(
    id            serial primary key,
    id_product    integer,
    amount        integer,
    date_delivery date
);

ALTER TABLE history
    ADD CONSTRAINT "history_fk0" FOREIGN KEY (id_product) REFERENCES product (id);

ALTER TABLE employees
    ADD CONSTRAINT "employees_fk0" FOREIGN KEY (id_position) REFERENCES positions (id);

ALTER TABLE dish
    ADD CONSTRAINT "dish_orders_fk0" FOREIGN KEY (id_orders) REFERENCES orders (id);

ALTER TABLE dish
    ADD CONSTRAINT "dish_menu_fk0" FOREIGN KEY (id_menu) REFERENCES menu (id);

ALTER TABLE orders
    ADD CONSTRAINT "orders_employees_fk0" FOREIGN KEY (id_employees) REFERENCES employees (id);

ALTER TABLE ingredient
    ADD CONSTRAINT "ingredient_menu_fk0" FOREIGN KEY (id_menu) REFERENCES menu (id);

ALTER TABLE ingredient
    ADD CONSTRAINT "ingredient_warehouse_fk0" FOREIGN KEY (id_warehouse) REFERENCES warehouse (id);

ALTER TABLE menu
    ADD CONSTRAINT "menu_unit_fk0" FOREIGN KEY (id_unit) REFERENCES unit (id);

ALTER TABLE warehouse
    ADD CONSTRAINT "warehouse_unit_fk0" FOREIGN KEY (id_unit) REFERENCES unit (id);

ALTER TABLE warehouse
    ADD CONSTRAINT "warehouse_product_fk0" FOREIGN KEY (id_product) REFERENCES product (id);