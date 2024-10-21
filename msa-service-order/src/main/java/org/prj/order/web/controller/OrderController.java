package org.prj.order.web.controller;

import lombok.RequiredArgsConstructor;
import org.prj.order.web.dto.RequestOrderDTO;
import org.prj.order.web.dto.ResponseOrderDTO;
import org.prj.order.web.entity.Order;
import org.prj.order.web.service.OrderService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;
import static java.util.UUID.randomUUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final Environment env;
    private final OrderService orderService;


    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }


    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrderDTO> createOrder(@PathVariable(name = "userId") String userId, @RequestBody RequestOrderDTO orderDTO) {
        Order order = Order.builder()
                .productId(orderDTO.getProductId())
                .qty(orderDTO.getQty())
                .unitPrice(orderDTO.getUnitPrice())
                .totalPrice(orderDTO.getQty() * orderDTO.getUnitPrice())
                .userId(userId)
                .orderId(randomUUID().toString())
                .build();

        Order createOrder = orderService.createOrder(order).get();

        ResponseOrderDTO result = ResponseOrderDTO
                .builder()
                .productId(createOrder.getProductId())
                .qty(createOrder.getQty())
                .unitPrice(createOrder.getUnitPrice())
                .totalPrice(createOrder.getTotalPrice())
                .createdAt(createOrder.getCreatedAt())
                .orderId(createOrder.getOrderId())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrderDTO>> getOrders(@PathVariable String userId) {
        Iterable<Order> orderList = orderService.getOrdersByUserId(userId);
        List<ResponseOrderDTO> result = new LinkedList<>();
        orderList.forEach(v ->
                result.add(ResponseOrderDTO.createOrderDTO(
                        v.getProductId()
                        , v.getQty()
                        , v.getUnitPrice()
                        , v.getTotalPrice(), v.getCreatedAt(), v.getOrderId())
                )
        );
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
