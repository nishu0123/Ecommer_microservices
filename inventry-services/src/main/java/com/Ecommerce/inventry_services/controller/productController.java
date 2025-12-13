package com.Ecommerce.inventry_services.controller;

import com.Ecommerce.inventry_services.clients.OrdersFeignClients;
import com.Ecommerce.inventry_services.dto.OrderItemRequestDto;
import com.Ecommerce.inventry_services.dto.OrdersRequestDto;
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
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

import static java.rmi.server.LogStream.log;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class productController {

    private final productService productService;

    private final ModelMapper modelmapper;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrdersFeignClients ordersFeignClients;

    /// allOrders is working
    @GetMapping("/allOrders")
    public ResponseEntity<List<productDto>> getAllInventry()
    {
        List<productDto> productDtoList = productService.getAllInventry();
        return ResponseEntity.ok(productDtoList);
    }

    //{id} is also working
    @GetMapping("/{id}")
    public ResponseEntity<productDto> getProductById(@PathVariable long id)
    {
        productDto inventories = productService.getProductById(id);
        return ResponseEntity.ok(inventories);
//        product productval = productService.getProductById(id);
//        productDto productDtoval = modelmapper.map(product , productDto.class);
//        return productDtoval;
    }

    //fetchOrders is now working
    @GetMapping("/fetchOrders")
    public String fetchFromOrderService()
    {

//        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();
//        String response = restClient.get()
//                .uri(orderService.getUri() + "/api/v1/Orders.core/helloOrder")
//                .retrieve()
//                .body(String.class);
//
////        System.print.out(response);
//
//        return response;

        //here i will use getmappping from inventory and
        //then  i will get from order service

        //using feign client
        return ordersFeignClients.getHelloOrders();

    }
    //home page is working
    @GetMapping("/")
    public String grettingOnInverntryService()
    {
        return "welcome to the home page of inventory service";
    }

    @PostMapping("/reduce-stock")
    ResponseEntity<Double> ReduceStock(@RequestBody OrdersRequestDto  ordersRequestDto)
    {
        log("reducing stock ..." + "orderRequestDto = " + ordersRequestDto);
        double totalPrice  = productService.reduceStock(ordersRequestDto);
        return ResponseEntity.ok(totalPrice);
        //here api call will be OrderRequestDto not OrderItemRequestDto
        //so loop through all the item
//        for(OrderItemRequestDto item : OrdersRequestDto)
//        {
//            productDto AvailableStock =productService.getProductById(item.getProductId());
//
//            //now fetch the required details about the product
//            int quantity = AvailableStock.getStock();
//            double price = AvailableStock.getPrice();
//
//            if(quantity <= item.getQuantity())
//            {
//
//            }
//        }
//        productDto AvailableStock = productService.getProductById(orderItemRequestDto.getProductId());

        //All the database operation will be done in the repository

//        if(AvailableStock.getStock() < orderItemRequestDto.getQuantity())
//        {
//            return ResponseEntity.unprocessableEntity().body(-1.0);
//        }else{
//            //reduce stock from the database and save the updated value to the database
//            productDto updatedStock = productService.reduceStock(orderItemRequestDto);//this function will reduce the stock
//            return ResponseEntity.unprocessableEntity().body(-1.0);
//        }

//        return ResponseEntity.ok(finalStock);


    }

    @PutMapping("/add-stock")
    ResponseEntity<Double> AddStock(@RequestBody OrderItemRequestDto orderItemRequestDto)
    {
        //this function has not been implemented
        double finalStock = 2;
        return ResponseEntity.ok(finalStock);
    }





}
//notebookLLM - upload pdf and get the report ( we can also convert it to podcast or video)
//we can do lot of things , with the pdf
//from pdf to generate PPT using this
//Zenspark - for PPT presentation
//Grok - we can do research on any tpoic , whatever have come into my mind
//google ai studio - for vibe coding