package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.business.ProductManager;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

/**
 * Domain: inventory.domain.com
 * Entity: Product
 * Version: 1
 */
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final HttpServletRequest request;

    private final ProductManager productManager;

    @Autowired
    public ProductController(HttpServletRequest request, ProductManager aManager) {
        this.productManager = aManager;
        this.request = request;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            KeycloakSecurityContext context = getKeycloakSecurityContext();
            if (context != null) {
                log.debug("Processing request for {}", context.getIdToken().getEmail());
            }
            Long productId = this.productManager.createProduct(productDTO);
            log.debug("Product created, productId is: {}", productId);
            return ResponseEntity.created(URI.create("/v1/product/" + productId)).build();
        } catch(RuntimeException re) {
            log.error("Error creating product", re);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        if (getKeycloakSecurityContext() != null) {
            log.debug("Processing request for {}", getKeycloakSecurityContext().getToken().getEmail());
        } else {
            log.debug("Cannot resolve Keycloak Security Context");
        }
        List<ProductDTO> results = this.productManager.getProducts(size, page);
        log.debug("Total results is {}", results.size());
        return ResponseEntity.ok(results);
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
