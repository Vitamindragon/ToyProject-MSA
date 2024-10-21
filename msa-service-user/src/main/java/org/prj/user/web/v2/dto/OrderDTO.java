package org.prj.user.web.v2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;
}
