package com.Ecommerce.inventry_services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service" , path = "/orders")
public interface OrdersFeignClients {

    @GetMapping("/core/helloOrder")
    String getHelloOrders();//this get mapping will return the string
}
