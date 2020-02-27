package com.cloudyeng.service.inventoryapi.business;

import com.cloudyeng.service.inventoryapi.dao.ProductDAO;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.cloudyeng.service.inventoryapi.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductManagerImplTest {

    private ProductManager manager;

    private ProductRepository repository;

    @Test
    public void testCreateExistingProduct() {
        repository = Mockito.mock(ProductRepository.class);
        manager = new ProductManagerImpl(repository);
        Mockito.when(repository.findBySku(Mockito.anyString())).thenReturn(100L);
        ProductDTO dto = new ProductDTO();
        dto.setSku("123123");
        dto.setDescription("An interesting snack");
        dto.setName("Snacky snack");
        dto.setProductType(ProductType.SNACK);

        try {
            manager.createProduct(dto);
        } catch(RuntimeException re) {
            assertThat(re).isNotNull();
        }

    }

    @Test
    public void testCreateProduct() {
        ProductDAO dao = new ProductDAO();
        dao.setProductId(1L);

        repository = Mockito.mock(ProductRepository.class);
        manager = new ProductManagerImpl(repository);
        Mockito.when(repository.findBySku(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.save(Mockito.any(ProductDAO.class))).thenReturn(dao);

        ProductDTO dto = new ProductDTO();
        dto.setSku("123123");
        dto.setDescription("An interesting snack");
        dto.setName("Snacky snack");
        dto.setProductType(ProductType.SNACK);

        Long productId = manager.createProduct(dto);
        assertThat(productId).isNotNull().isEqualTo(1L);
    }

    @Test
    public void testGetProductSuccess() {
        repository = Mockito.mock(ProductRepository.class);
        manager = new ProductManagerImpl(repository);

        ProductDAO dao = new ProductDAO();
        dao.setProductId(1234L);
        dao.setDisplayName("Drinky drink");
        dao.setProductType(ProductType.BEVERAGE.name());
        dao.setSku("7549202");

        Optional<ProductDAO> value = Optional.of(dao);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(value);

        ProductDTO dto = manager.getProduct(1234L);
        assertThat(dto).isNotNull();
        assertThat(dto.getSku()).isEqualTo(dao.getSku());
    }

}
