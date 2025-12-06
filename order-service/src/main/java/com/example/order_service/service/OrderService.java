package com.example.order_service.service;

import com.example.order_service.dto.OrderItemRequestDto;
import com.example.order_service.dto.OrdersRequestDto;
import com.example.order_service.entity.Orders;
import com.example.order_service.repository.OrdersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ModelMapper modelMapper;

//below whatever have been implemented is about the order items , i think this is not the
    //correct way
//    public  List<OrderItems> getOrderItembyId(Integer id)
//    {
//       List<OrderItems> orderItems = null; // at this time there no any order items in this case
//       return orderItems;
//    }

    public List<OrderItemRequestDto> getAllOrders()
    {
        //here we have to fetch the all orders
        List<Orders> allOrders = ordersRepository.findAll();

        //to match the return type
        //to acheive this we have to inject the model mapper
        /*
        the below code is the called injecting the model mapper
        @Autowired
    private ModelMapper modelMapper;
         */

        return allOrders.stream()
                .map(entity-> modelMapper.map(entity , OrderItemRequestDto.class))
                .toList();
    }
    public OrdersRequestDto getOrderById(long id)
    {
        Orders orders = ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return modelMapper.map(orders , OrdersRequestDto.class);
    }


}
