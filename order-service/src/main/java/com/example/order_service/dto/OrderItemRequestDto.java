package com.example.order_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItemRequestDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private long productId;
    private Integer quantity;
}
