package org.prj.order.message.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.prj.order.message.queue.dto.Field;
import org.prj.order.message.queue.dto.KafkaOrderDto;
import org.prj.order.message.queue.dto.Payload;
import org.prj.order.message.queue.dto.Schema;
import org.prj.order.web.dto.RequestOrderDTO;
import org.prj.order.web.dto.ResponseOrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    // 불변 필드 초기화
    private static final List<Field> FIELDS = Arrays.asList(
            new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("int32", true, "qty"),
            new Field("int32", true, "unit_price"),
            new Field("int32", true, "total_price")
    );

    private static final Schema SCHEMA = Schema.builder()
            .type("struct")
            .fields(FIELDS)
            .optional(false)
            .name("orders")
            .build();

    // Kafka 메시지 전송
    public void send(String topic, ResponseOrderDTO orderDto) {
        Payload payload = Payload.builder()
                .order_id(orderDto.getOrderId())
                .user_id(orderDto.getUserId())
                .product_id(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unit_price(orderDto.getUnitPrice())
                .total_price(orderDto.getTotalPrice())
                .build();

        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(SCHEMA, payload);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Order Producer sent data from the Order microservice: " + kafkaOrderDto);

    }

    // DTO를 JSON 문자열로 변환
    private String convertToJson(RequestOrderDTO orderDto) {
        try {
            return mapper.writeValueAsString(orderDto);
        } catch (JsonProcessingException ex) {
            log.error("JSON conversion error for OrderDTO: {}", orderDto, ex);
            return null;
        }
    }
}
