package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private long productId;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
}
