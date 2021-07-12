CREATE MATERIALIZED VIEW product_latest_prices as select a.product_id as product_id,
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
                                                       select allprices.price_id from (select prices.*, row_number() over (partition by prices.product_id order by end_date) as seqnum
                                                                                       from (
                                                                                                select *
                                                                                                from product_prices
                                                                                                where current_date between effective_date and end_date
                                                                                                union
                                                                                                select *
                                                                                                from product_prices
                                                                                                where end_date is null) as prices) as allprices
                                                       where allprices.seqnum = 1 order by product_id
                                                   ) order by product_id;
