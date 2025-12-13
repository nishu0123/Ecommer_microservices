package com.example.order_service.dto;

import com.example.order_service.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//i want to learn more about the Dto , like in detail i want to know it is used for managing the data

@Getter
@Setter
@NoArgsConstructor
public class OrdersRequestDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    private long id;
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus;
//    private double price;

    List<OrderItemRequestDto> items;
}
