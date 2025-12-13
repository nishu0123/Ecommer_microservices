package com.example.order_service.clients;

import com.example.order_service.dto.OrdersRequestDto;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service" ,  path = "/product")
public interface inventoryOpenFeignClient {
    @PostMapping("/reduce-stock")
    Double reduceStock(@RequestBody OrdersRequestDto ordersRequestDto);
}
/*

@FeignClient(name = "order-service" , path = "/orders")
public interface OrdersFeignClients {

    @GetMapping("/core/helloOrder")
    String getHelloOrders();//this get mapping will return the string
}
*/