package com.Ecommerce.inventry_services.dto;

import lombok.Data;

@Data
public class productDto {
    private long id;
    private String title;
    private Double price;
    private Integer stock;
}
