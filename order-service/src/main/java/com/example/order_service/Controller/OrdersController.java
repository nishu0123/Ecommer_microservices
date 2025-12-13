package com.example.order_service.Controller;

import com.example.order_service.clients.inventoryOpenFeignClient;
import com.example.order_service.dto.OrderItemRequestDto;
import com.example.order_service.dto.OrdersRequestDto;
import com.example.order_service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import static java.rmi.server.LogStream.log;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    private  inventoryOpenFeignClient inventoryOpenFeignClient;
    @GetMapping("/hello")
    String greetUser()
    {
        return "hello welcome to the order service!";
    }

//    @GetMapping("ordersItems")
//
//    public List<OrderItems> getOrders(HttpServletRequest req, HttpServletResponse res) {
//        Integer id = Integer.valueOf(req.getParameter("id"));  // extract id from request
//        List<OrderItems> orderItems = OrderService1.getOrderItemById (id);
//        return orderItems;
//    }

    /// orders is working
    @GetMapping("/allOrders")
    ResponseEntity<List<OrderItemRequestDto>> getOrder(HttpServletRequest request)
    {
        List<OrderItemRequestDto> orderItemRequestDtoList = orderService.getAllOrders();
        return ResponseEntity.ok(orderItemRequestDtoList);
    }

    //helloOrder is also working
    @GetMapping("/helloOrder")
    public String helloOrder()
    {
        return "hello order from order services";
    }


    @GetMapping("orders/id")
    ResponseEntity<OrdersRequestDto> getOrderById(@PathVariable long id)
    {
        OrdersRequestDto ordersRequestDto = orderService.getOrderById(id);
        return ResponseEntity.ok(ordersRequestDto);
    }


    //below gate mapping is working
    @GetMapping("/")
    public  String greetFromOrderService()
    {
        return "welcome to the home page of the order services ";
    }

    @PostMapping("/create-order") /* here we have to create the order and also save that data into the database */
    ResponseEntity<OrdersRequestDto> getPrice(@RequestBody OrdersRequestDto ordersRequestDto)
    {
        //from here we will call the reduce order method from inventory -service
        //here i have to map the order-service dto to inventory-service dto
        //then only data will be traferred
//        log("Creating order..." + );
        log("creating order ..." + "orderRequestDto = " + ordersRequestDto);
        //here an order-service will be created and in that this will be passed
//        Double totalPrice = inventoryOpenFeignClient.reduceStock(ordersRequestDto); // no use at this point
        OrdersRequestDto ordersRequestDto1 = orderService.createOrder(ordersRequestDto);
        return ResponseEntity.ok(ordersRequestDto1);
    }






}
