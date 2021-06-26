package com.cloudyeng.service.inventoryapi.repository;

import com.cloudyeng.service.inventoryapi.dto.PricedProductDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PricedProductRepository implements PanacheRepository<PricedProductDTO> {

    String query = "select a.product_id as product_id,\n" +
            "       a.display_name as display_name,\n" +
            "       a.description as description,\n" +
            "       a.type as type,\n" +
            "       a.sku as sku,\n" +
            "       aa.price_id as price_id,\n" +
            "       aa.price as price,\n" +
            "       aa.effective_date as effective_date\n" +
            "from product a\n" +
            "join product_prices aa on a.product_id = aa.product_id\n" +
            "where aa.price_id in (\n" +
            "    select p.price_id\n" +
            "    from (select p.*, row_number() over (partition by product_id order by effective_date desc) as seqnum from product_prices p) p\n" +
            "    where seqnum = 1\n" +
            ");";

    public List<PricedProductDTO> findProductsWithLatestPrice() {
        return find(query).list();
    }
}
