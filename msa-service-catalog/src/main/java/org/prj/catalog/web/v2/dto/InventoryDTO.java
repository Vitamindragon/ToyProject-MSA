package org.prj.catalog.web.v2.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class InventoryDTO {
    private String productId;
    private String productName;
    private Integer stock;
    private Integer unitPrice;
    private Date createdAt;

    public InventoryDTO(String productId, String productName, Integer stock, Integer unitPrice, Date createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
    }

    public static InventoryDTO createInventoryDTO(String productId, String productName, Integer stock, Integer unitPrice, Date createdAt) {
        return new InventoryDTO(productId, productName, stock, unitPrice, createdAt);
    }
}