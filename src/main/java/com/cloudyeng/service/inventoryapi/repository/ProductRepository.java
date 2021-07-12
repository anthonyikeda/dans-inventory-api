package com.cloudyeng.service.inventoryapi.repository;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductDAO> {

    public Long findBySku(String sku) {
        ProductDAO found = find("sku", sku).firstResult();
        if(found != null) {
            return found.getProductId();
        } else {
            return null;
        }
    }

}
