package org.prj.order.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestOrderDTO implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}