package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@Testcontainers
public class ProductManagerImplTest {

    private final Logger log = LoggerFactory.getLogger(ProductManagerImplTest.class);

    @Inject
    ProductManagerImpl manager;

    @Test
    public void testCreateExistingProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setSku("42123");
        dto.setDescription("Creamy, foamy coffee");
        dto.setName("Cafe Latte");
        dto.setProductType(ProductType.BEVERAGE);

        try {
            Long productId = manager.createProduct(dto);
            assertThat(productId).isNull();
        } catch(ProductExistsException e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    public void testCreateProduct() {
        ProductDTO dto = new ProductDTO();
        dto.setSku("123123");
        dto.setDescription("An interesting snack");
        dto.setName("Snacky snack");
        dto.setProductType(ProductType.SNACK);

        try {
            Long productId = manager.createProduct(dto);
            assertThat(productId).isNotNull().isGreaterThan(1L);
        } catch(ProductExistsException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testGetProductSuccess() {
        ProductDTO dto = manager.getProduct(2L);
        assertThat(dto).isNotNull();
        assertThat(dto.getSku()).isEqualTo("53234234");
    }

}
