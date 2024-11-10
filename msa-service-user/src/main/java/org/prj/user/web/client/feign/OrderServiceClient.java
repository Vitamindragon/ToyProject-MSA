package org.prj.user.web.client.feign;

import org.prj.user.web.dto.ResponseOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="msa-service-order")
public interface OrderServiceClient {

    @GetMapping("/orders/users/{userId}/orders")
    List<ResponseOrderDTO> getOrders(@PathVariable("userId") String userId);
}
