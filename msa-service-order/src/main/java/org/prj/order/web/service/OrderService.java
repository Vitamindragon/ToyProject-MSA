package org.prj.order.web.service;

import org.prj.order.web.entity.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> createOrder(Order order);
    Optional<Order> getOrderByOrderId(String orderId);
    Iterable<Order> getOrdersByUserId(String userId);
}