package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.business.ProductManager;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Domain: inventory.domain.com
 * Entity: Product
 * Version: 1
 */
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductManager productManager;

    @Autowired
    public ProductController(ProductManager aManager) {
        this.productManager = aManager;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Long productId = this.productManager.createProduct(productDTO);
            log.debug("Product created, productId is: {}", productId);
            return ResponseEntity.created(URI.create("/v1/product/" + productId)).build();
        } catch(RuntimeException re) {
            log.error("Error creating product", re);
            return ResponseEntity.badRequest().build();
        }
    }
}
