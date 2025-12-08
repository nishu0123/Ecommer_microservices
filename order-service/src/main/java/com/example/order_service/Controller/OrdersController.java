package com.example.order_service.Controller;

import com.example.order_service.dto.OrderItemRequestDto;
import com.example.order_service.dto.OrdersRequestDto;
import com.example.order_service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
public class OrdersController {
    @Autowired
    private OrderService orderService;
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

    @GetMapping("/orders")
    ResponseEntity<List<OrderItemRequestDto>> getOrder(HttpServletRequest request)
    {
        List<OrderItemRequestDto> orderItemRequestDtoList = orderService.getAllOrders();
        return ResponseEntity.ok(orderItemRequestDtoList);
    }

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





}
