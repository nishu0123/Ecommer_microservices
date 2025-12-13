package com.example.order_service.service;

import com.example.order_service.clients.inventoryOpenFeignClient;
import com.example.order_service.dto.OrderItemRequestDto;
import com.example.order_service.dto.OrdersRequestDto;
import com.example.order_service.entity.OrderItems;
import com.example.order_service.entity.OrderStatus;
import com.example.order_service.entity.Orders;
import com.example.order_service.repository.OrdersRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    private final inventoryOpenFeignClient inventoryOpenFeignClient;
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

    @Retry(name = "inventoryRetry" , fallbackMethod = "fallbackcreateOrder")
    public  OrdersRequestDto createOrder(@RequestBody OrdersRequestDto ordersRequestDto)
    {
        //here we have to create the order
        //first fetch the total price from the inventory-service
        Double totalPrice = inventoryOpenFeignClient.reduceStock(ordersRequestDto); // reduce stock api is working fine

        //now we have to create the order which will contain the tota price of the order
        //and it will also contain the other information
        Orders order = modelMapper.map(ordersRequestDto , Orders.class);

        //this is the same order , but model-mapper will create different order for each item
        //so lets make the same order for all items
        for(OrderItems orderItems : order.getItems())
        {
            orderItems.setOrders(order);
        }

        //now set the total price to the order
        order.setPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED); //set the order status
        Orders savedOrder = ordersRepository.save(order); // save the order created into the databsae


//        Orders orders1 = new Orders();
//        orders1.setOrderStatus(OrderStatus.CONFIRMED);
//        orders1.setPrice(totalPrice);
//        orders1.setId(order.getId());
//        ordersRepository.save(orders1);


        return modelMapper.map(savedOrder , ordersRequestDto.getClass());
    }




    //here methodname and the return type should be same with the method for which we are writing the fallback method
    public  OrdersRequestDto fallbackcreateOrder( OrdersRequestDto ordersRequestDto , Throwable throwable)
    {

//        log("fallback method is called on failing of createOrder ");

        log.error("something went wrong!!!");
        return new OrdersRequestDto(); //it will return everything null
    }
}
