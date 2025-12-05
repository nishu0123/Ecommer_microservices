package com.example.order_service.service;

import com.example.order_service.entity.OrderItems;
import com.example.order_service.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService {
    @Autowired
    private OrdersRepository orderRepositroy;


    public  List<OrderItems> getOrderItembyId(Integer id)
    {
       List<OrderItems> orderItems = null; // at this time there no any order items in this case
       return orderItems;
    }

}
