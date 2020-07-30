package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.cloudyeng.service.inventoryapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductManagerImpl implements ProductManager {

    private final Logger log = LoggerFactory.getLogger(ProductManagerImpl.class);

    private final ProductRepository productRepository;

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

    @Override
    public List<ProductDTO> getProducts(int limit, int page) {
        log.debug("Loading products with page {} and size {}", page, limit);
        Pageable resultPage = PageRequest.of(page, limit);
        return this.productRepository.findAll(resultPage)
                .map(this::toDto)
                .stream()
                .collect(Collectors.toList());
    }

    private ProductDTO toDto(ProductDAO dao) {
        ProductDTO dto = new ProductDTO();
        dto.setProductType(ProductType.valueOf(dao.getProductType().toUpperCase()));
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
