package com.example.order_service.Controller;

import com.example.order_service.entity.OrderItems;
import com.example.order_service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService OrderService1;
    @GetMapping("/hello")
    String greetUser()
    {
        return "hello welcome to the order service!";
    }

    @GetMapping("ordersItems")

    public List<OrderItems> getOrders(HttpServletRequest req, HttpServletResponse res) {
        Integer id = Integer.valueOf(req.getParameter("id"));  // extract id from request
        List<OrderItems> orderItems = OrderService1.getOrderItembyId(id);
        return orderItems;
    }


}
