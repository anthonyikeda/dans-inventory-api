package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.cloudyeng.service.inventoryapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductManagerImpl implements ProductManager {

    private Logger log = LoggerFactory.getLogger(ProductManagerImpl.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductManagerImpl(ProductRepository aRepository) {
        this.productRepository = aRepository;
    }

    @Override
    public Long createProduct(ProductDTO productDTO) {
        Long foundId = productRepository.findBySku(productDTO.getSku());

        if(foundId != null) {
            //throw Exception
            log.error("Product with sku {} found. Cannot create new entry.", productDTO.getSku());
            throw new RuntimeException("SKU Already Exists");
        } else {
            log.debug("Product SKU does not exist! Creating new product.");
            ProductDAO dao = toDao(productDTO);
            ProductDAO saved = this.productRepository.save(dao);
            return saved.getProductId();
        }
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        return this.productRepository.findById(productId)
                .map(this::toDto).get();
    }

    private ProductDTO toDto(ProductDAO dao) {
        ProductDTO dto = new ProductDTO();
        dto.setProductType(ProductType.valueOf(dao.getProductType()));
        dto.setName(dao.getDisplayName());
        dto.setDescription(dao.getDescription());
        dto.setSku(dao.getSku());
        return dto;
    }

    private ProductDAO toDao(ProductDTO dto) {
        ProductDAO dao = new ProductDAO();

        dao.setDisplayName(dto.getName());
        dao.setDescription(dto.getDescription());
        dao.setProductType(dto.getProductType().name());
        dao.setSku(dto.getSku());
        return dao;
    }
}
