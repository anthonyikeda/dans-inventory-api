package com.cloudyeng.service.inventoryapi.repository;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import com.cloudyeng.service.inventoryapi.dto.PricedProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, Long> {

    @Query(value = "Select p.productId from Product p where p.sku = ?1")
    Long findBySku(String sku);


    @Query(nativeQuery = true,
    value = "select a.product_id as product_id,\n" +
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
            ");")
    Set<PricedProductDTO> findProductsWithLatestPrice();
}
