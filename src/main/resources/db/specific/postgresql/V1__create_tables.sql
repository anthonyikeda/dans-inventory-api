create table product (
    product_id bigint generated always as IDENTITY,
    display_name varchar(180),
    description text,
    type varchar(20),
    sku varchar(40) not null,
    created timestamp,
    created_by varchar(50),
    last_updated timestamp
);

alter table product add constraint pk_product_id primary key(product_id);
create unique index idx_sku on product(sku);

create table inventory (
    product_id bigint not null,
    count integer not null,
    last_ordered timestamp,
    requested_on timestamp
);
alter table inventory add constraint pk_inventory primary key (product_id);
alter table inventory add constraint fk_product_inventory foreign key (product_id) references product(product_id);

create table product_prices (
    price_id bigint generated always as identity,
    product_id bigint not null,
    price money not null default '0.00',
    effective_date timestamp,
    end_date timestamp,
    default_price boolean default 'false'
);

alter table product_prices add constraint pk_product_price primary key (price_id);
alter table product_prices add constraint fk_product_price foreign key (product_id) references product(product_id);


create table inventory_orders (
    order_id bigint generated always as identity,
    order_status varchar(20) NOT NULL,
    product_id bigint not null,
    quantity numeric not null,
    date_ordered timestamp not null,
    ordered_by varchar(120) not null,
    date_fulfilled timestamp,
    quantity_fulfilled int
);
alter table inventory_orders add constraint pk_order primary key (order_id);
alter table inventory_orders add constraint fk_inventory_order foreign key (product_id) references inventory(product_id);
