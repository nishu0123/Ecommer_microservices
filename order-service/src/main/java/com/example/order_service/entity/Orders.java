package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.Cascade;

import java.util.List;

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
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<OrderItems> items;
}
