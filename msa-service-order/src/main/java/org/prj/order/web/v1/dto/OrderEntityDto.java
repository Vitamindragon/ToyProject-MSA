package org.prj.order.web.v1.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntityDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}