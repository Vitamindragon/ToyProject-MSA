package org.prj.catalog.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class CatalogDTO {
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private Date createdAt;

    public CatalogDTO(String productId, String productName, Integer stock, Integer unitPrice, Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
    }

    public static CatalogDTO createCatalogDTO(String productId, String productName, Integer stock, Integer unitPrice, Date createdAt) {
        return new CatalogDTO(productId, productName, stock, unitPrice, createdAt);
    }
}