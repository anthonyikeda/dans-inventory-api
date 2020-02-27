package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dto.ProductDTO;

public interface ProductManager {

    Long createProduct(ProductDTO productDTO);

    ProductDTO getProduct(Long productId);
}
