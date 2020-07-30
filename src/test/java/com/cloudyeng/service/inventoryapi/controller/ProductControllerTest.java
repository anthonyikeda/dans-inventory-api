package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.FlywayTestExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({FlywayTestExtension.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @FlywayTest
    @Disabled
    public void shouldReturnCreated() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("Cafe latte");
        dto.setDescription("A creamy, frothy drink");
        dto.setProductType(ProductType.BEVERAGE);
        dto.setSku("57389283");
        String[] labels = new String[]{"coffee", "popular", "foam"};
        dto.setLabels(labels);

        String body = mapper.writeValueAsString(dto);

        this.mockMvc.perform(
                post("/v1/product")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", String.format("Basic %s", Base64Utils.encodeToString("user:letmein".getBytes())))
        ).andDo(print())
                .andExpect(status().isCreated());
    }
}
