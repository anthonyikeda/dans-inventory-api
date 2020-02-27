create table product (
    product_id bigint generated always as IDENTITY,
    display_name varchar(180),
    description clob,
    type varchar(20),
    sku varchar(40) not null unique ,
    created timestamp,
    created_by varchar(50),
    last_updated timestamp
);

alter table product add constraint pk_product_id primary key(product_id);

create table inventory (
    product_id bigint not null,
    count integer not null,
    last_ordered timestamp,
    requested_on timestamp
);

alter table inventory add constraint fk_product_inventory foreign key (product_id) references product(product_id);

create table product_prices (
    price_id bigint generated always as identity,
    product_id bigint not null,
    price decimal(5,2) default 0.00,
    effective_date timestamp,
    end_date timestamp,
    default_price boolean default 'false'
);

alter table product_prices add constraint pk_product_price primary key (price_id);
alter table product_prices add constraint fk_product_price foreign key (product_id) references product(product_id);


