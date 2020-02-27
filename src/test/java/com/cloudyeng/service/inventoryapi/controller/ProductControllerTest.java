package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnNotImplemented() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("Cafe latte");
        dto.setDescription("A creamy, frothy drink");
        dto.setProductType(ProductType.BEVERAGE);
        String[] labels = new String[]{"coffee", "popular", "foam"};
        dto.setLabels(labels);

        String body = mapper.writeValueAsString(dto);

        this.mockMvc.perform(
                post("/v1/product")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", String.format("Basic %s", Base64Utils.encodeToString("user:letmein".getBytes())))
        ).andDo(print())
                .andExpect(status().isNotImplemented());
    }
}
