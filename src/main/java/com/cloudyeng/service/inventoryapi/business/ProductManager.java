package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dto.ProductDTO;

import java.util.List;

public interface ProductManager {

    Long createProduct(ProductDTO productDTO);

    ProductDTO getProduct(Long productId);

    List<ProductDTO> getProducts(int limit, int offset);
}
