package org.prj.order.web.v1.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.prj.order.web.v1.dto.OrderEntityDto;
import org.prj.order.web.v1.entity.OrderEntity;
import org.prj.order.web.v1.repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {
    OrderEntityRepository orderRepository;

    @Autowired
    public OrderEntityServiceImpl(OrderEntityRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntityDto createOrder(OrderEntityDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderEntity orderEntity = mapper.map(orderDto, OrderEntity.class);

        orderRepository.save(orderEntity);

        OrderEntityDto returnValue = mapper.map(orderEntity, OrderEntityDto.class);

        return returnValue;
    }

    @Override
    public OrderEntityDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderEntityDto orderDto = new ModelMapper().map(orderEntity, OrderEntityDto.class);

        return orderDto;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}