package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import com.cloudyeng.service.inventoryapi.dto.ProductType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProductControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
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

        String authHeader = new String(Base64.getEncoder().encode("user:letmein".getBytes()));

        given().log().all()
                .body(body)
                .header("Authorization", String.format("Basic %s", authHeader))
                .contentType(ContentType.fromContentType("application/json"))
                .when()
                .post("/v1/product")
                .then()
                .statusCode(201);
    }
}
