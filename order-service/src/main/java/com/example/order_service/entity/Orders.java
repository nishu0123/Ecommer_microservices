package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private double price;

}
