package org.prj.order.web.dto;

import lombok.Data;

@Data
public class RequestOrderDTO {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}
