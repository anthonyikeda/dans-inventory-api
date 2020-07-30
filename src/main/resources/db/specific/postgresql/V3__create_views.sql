create view product_latest_prices as
select a.product_id as product_id,
       a.display_name as display_name,
       a.description as description,
       a.type as type,
       a.sku as sku,
       aa.price_id as price_id,
       aa.price as price,
       aa.effective_date as effective_date
from product a
         join product_prices aa on a.product_id = aa.product_id
where aa.price_id in (
    select p.price_id
    from (select p.*, row_number() over (partition by product_id order by effective_date desc) as seqnum from product_prices p) p
    where seqnum = 1
);
