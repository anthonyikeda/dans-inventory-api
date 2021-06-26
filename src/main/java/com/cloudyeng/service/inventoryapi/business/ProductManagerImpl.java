package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.cloudyeng.service.inventoryapi.repository.PricedProductRepository;
import com.cloudyeng.service.inventoryapi.repository.ProductRepository;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductManagerImpl {

    private final Logger log = LoggerFactory.getLogger(ProductManagerImpl.class);

    @Inject
    ProductRepository productRepository;

    @Inject
    PricedProductRepository pricedProductRepository;

    @Transactional
    public Long createProduct(ProductDTO productDTO) throws ProductExistsException {
        Long foundId = productRepository.findBySku(productDTO.getSku());

        if(foundId != null) {
            //throw Exception
            log.error("Product with sku {} found. Cannot create new entry.", productDTO.getSku());
            throw new ProductExistsException(String.format("SKU Already Exists: %s", productDTO.getSku()));
        } else {
            log.debug("Product SKU does not exist! Creating new product.");
            ProductDAO dao = toDao(productDTO);
            this.productRepository.persist(dao);
            return dao.getProductId();
        }
    }

    public ProductDTO getProduct(Long productId) {
        ProductDAO dao = this.productRepository.findById(productId);
        return toDto(dao);
    }

    public List<ProductDTO> getProducts(int limit, int page) {
        log.debug("Loading products with page {} and size {}", page, limit);

        return this.productRepository.findAll(Sort.ascending("product_id")).page(page, limit).list()
                .stream()
                .map(this::toDto)
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
