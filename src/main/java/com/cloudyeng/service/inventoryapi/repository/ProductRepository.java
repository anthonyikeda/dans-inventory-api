package com.cloudyeng.service.inventoryapi.repository;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, Long> {

    @Query(value = "Select p.productId from Product p where p.sku = ?1")
    Long findBySku(String sku);
}
