insert into product(display_name, description, type, sku, created, created_by, last_updated)
values('Cafe Latte', 'Creamy, foamy coffee', 'beverage', '42123', current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('Flat white', 'Basic coffee', 'beverage', '53234234',  current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('Cuppacino', 'Creamy, foamy coffee with a dusting of chocolate', 'beverage', '8542234', current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('English Breakfast tea', 'Wholesome hearty tea', 'beverage', '1234245', current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('Mocha', 'Chocolate inspired coffee', 'beverage', '5234234', current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('Blueberry Muffin', 'Large snack', 'snack', '2312312', current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('Cranberry Scone', 'Wheaty snack', 'snack', '1134634', current_timestamp, 'migrations', current_timestamp);

insert into product(display_name, description, type, sku,  created, created_by, last_updated)
values('Breakfast roll', 'Egg, Bacon and cheese on a bread roll', 'snack', '1256732', current_timestamp, 'migrations', current_timestamp);

commit;

-- product prices
insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (1, 4.50, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (2, 3.50, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (2, 4.50, '2020-03-15 12:00:00', '2020-04-15 12:00:00', false);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (3, 5.00, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (4, 2.50, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (5, 3.50, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (6, 4.00, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (7, 3.00, '2020-01-01 12:00:00', null, true);

insert into product_prices(product_id, price, effective_date, end_date, default_price)
values (8, 6.00, '2020-01-01 12:00:00', null, true);

commit;

-- inventory values
insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (1, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (2, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (3, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (4, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (5, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (6, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (7, 200, '2019-12-09 10:00:00', null);

insert into inventory(product_id, "count", last_ordered, requested_on)
VALUES (8, 200, '2019-12-09 10:00:00', null);

commit;
