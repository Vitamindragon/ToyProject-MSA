package org.prj.order.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@Builder
@JsonInclude(NON_NULL)
public class ResponseOrderDTO {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;

    public static ResponseOrderDTO createOrderDTO(String productId, Integer qty, Integer unitPrice, Integer totalPrice, Date createdAt, String orderId) {
        return new ResponseOrderDTO(productId, qty, unitPrice, totalPrice, createdAt, orderId);

    }

    public ResponseOrderDTO(String productId, Integer qty, Integer unitPrice, Integer totalPrice, Date createdAt, String orderId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.orderId = orderId;
    }
}