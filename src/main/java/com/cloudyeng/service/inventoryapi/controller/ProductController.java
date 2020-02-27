package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Domain: inventory.domain.com
 * Entity: Product
 * Version: 1
 */
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
