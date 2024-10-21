package org.prj.order.web.service;

import lombok.RequiredArgsConstructor;
import org.prj.order.web.entity.Order;
import org.prj.order.web.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> createOrder(Order order) {
        return ofNullable(orderRepository.save(order));
    }

    @Override
    public Optional<Order> getOrderByOrderId(String orderId) {
        return ofNullable(orderRepository.findByOrderId(orderId));
    }

    @Override
    public Iterable<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
