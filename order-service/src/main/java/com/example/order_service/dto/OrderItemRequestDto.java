package com.example.order_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemRequestDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private Long productId;
    private Integer quantity;
}
