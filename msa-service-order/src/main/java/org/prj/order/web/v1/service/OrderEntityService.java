package org.prj.order.web.v1.service;

import org.prj.order.web.v1.dto.OrderEntityDto;
import org.prj.order.web.v1.entity.OrderEntity;

public interface OrderEntityService {
    OrderEntityDto createOrder(OrderEntityDto orderDetails);
    OrderEntityDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}