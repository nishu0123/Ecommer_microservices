package com.Ecommerce.inventry_services.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class product {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private Double price;
    private Integer stock;
}
