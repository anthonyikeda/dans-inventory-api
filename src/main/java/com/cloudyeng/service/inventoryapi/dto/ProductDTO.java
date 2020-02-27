package com.cloudyeng.service.inventoryapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private ProductType productType;

    @JsonProperty
    private String[] labels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}
