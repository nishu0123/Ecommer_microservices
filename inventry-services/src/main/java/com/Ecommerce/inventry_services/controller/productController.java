package com.Ecommerce.inventry_services.controller;

import com.Ecommerce.inventry_services.dto.productDto;
import com.Ecommerce.inventry_services.entity.product;
import com.Ecommerce.inventry_services.services.productService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class productController {

    private final productService productService;

    private final ModelMapper modelmapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @GetMapping("/allOrders")
    public ResponseEntity<List<productDto>> getAllInventry()
    {
        List<productDto> productDtoList = productService.getAllInventry();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<productDto> getProductById(@PathVariable long id)
    {
        productDto inventories = productService.getProductById(id);
        return ResponseEntity.ok(inventories);
//        product productval = productService.getProductById(id);
//        productDto productDtoval = modelmapper.map(product , productDto.class);
//        return productDtoval;
    }

    @GetMapping("/fetchOrders")
    public String fetchFromOrderService()
    {
        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();
        String response = restClient.get()
                .uri(orderService.getUri() + "/api/v1/Orders.core/helloOrder")
                .retrieve()
                .body(String.class);

//        System.print.out(response);

        return response;

        //here i will use getmappping from inventory and
        //then  i will get from order service

    }
    @GetMapping("/")
    public String grettingOnInverntryService()
    {
        return "welcome to the home page of inventory services";
    }

    

}
//notebookLLM - upload pdf and get the report ( we can also convert it to podcast or video)
//we can do lot of things , with the pdf
//from pdf to generate PPT using this
//Zenspark - for PPT presentation
//Grok - we can do research on any tpoic , whatever have come into my mind
//google ai studio - for vibe coding