package com.cloudyeng.service.inventoryapi.repository;

import com.cloudyeng.service.inventoryapi.business.ProductNotFoundException;
import com.cloudyeng.service.inventoryapi.dao.PricedProductDAO;
import com.cloudyeng.service.inventoryapi.dto.PricedProductDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PricedProductRepository implements PanacheRepository<PricedProductDAO> {
    public List<PricedProductDTO> findProductsWithLatestPrice() {
        return findAll()
                .list()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PricedProductDTO findProductWithLatestPrice(Long productId) throws ProductNotFoundException {
        return findByIdOptional(productId)
                .map(this::toDTO)
                .orElseThrow(() -> new ProductNotFoundException(String.format("No product with id %d found", productId)));
    }

    private PricedProductDTO toDTO(PricedProductDAO dao) {
        NumberFormat nb = NumberFormat.getInstance();
        nb.setMinimumFractionDigits(2);
        nb.setMaximumFractionDigits(2);

        PricedProductDTO toReturn = new PricedProductDTO();
        toReturn.setProductId(dao.getProductId());
        toReturn.setDisplayName(dao.getDisplayName());
        toReturn.setDescription(dao.getDescription());
        toReturn.setType(dao.getProductType());
        toReturn.setSku(dao.getSku());
        toReturn.setPriceId(dao.getPriceId());
        toReturn.setPrice(nb.format(dao.getPrice()));
        toReturn.setEffectiveDate(new Date(dao.getEffectiveDate().getTime()));
        return toReturn;
    }
}
