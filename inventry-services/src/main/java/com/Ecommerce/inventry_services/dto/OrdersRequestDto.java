package com.Ecommerce.inventry_services.dto;

//import com.example.order_service.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


//i want to learn more about the Dto , like in detail i want to know it is used for managing the data
@Data
public class OrdersRequestDto {
    private List<OrderItemRequestDto> items;
}
