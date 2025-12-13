package com.Ecommerce.inventry_services.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderItemRequestDto {
    private Long productId;
    private Integer quantity;
}
