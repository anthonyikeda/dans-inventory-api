package com.cloudyeng.service.inventoryapi;

import com.cloudyeng.service.inventoryapi.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InventoryApiApplicationTests {

    @Autowired
    private ProductController productController;

    @Test
    void contextLoads() {
        assertThat(productController).isNotNull();
    }

}
