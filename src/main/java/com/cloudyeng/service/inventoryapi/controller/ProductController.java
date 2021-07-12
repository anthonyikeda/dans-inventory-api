package com.cloudyeng.service.inventoryapi.controller;

import com.cloudyeng.service.inventoryapi.business.ProductExistsException;
import com.cloudyeng.service.inventoryapi.business.ProductManagerImpl;
import com.cloudyeng.service.inventoryapi.dto.ProductDTO;
import io.quarkus.security.Authenticated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Domain: inventory.domain.com
 * Entity: Product
 * Version: 1
 */

@Path("/v1/product")
@Authenticated
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Inject
    ProductManagerImpl productManager;


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"ui-admin"})
    public Response createProduct(ProductDTO productDTO) {
        try {
            // log.debug("Processing request for {}", context.getIdToken().getEmail());
            Long productId = this.productManager.createProduct(productDTO);
            log.debug("Product created, productId is: {}", productId);
            return Response.created(URI.create("/v1/product/" + productId)).build();
        } catch(ProductExistsException re) {
            log.error("Error creating product", re);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed({"ui-shopper", "ui-admin"})
    public Response getProducts(@QueryParam("page") @DefaultValue("0") int page,
                                                        @QueryParam("size") @DefaultValue("10") int size) {
        // log.debug("Processing request for {}", getKeycloakSecurityContext().getToken().getEmail());
        List<ProductDTO> results = this.productManager.getProducts(size, page);
        log.debug("Total results is {}", results.size());
        return Response.ok(results).build();
    }

}
