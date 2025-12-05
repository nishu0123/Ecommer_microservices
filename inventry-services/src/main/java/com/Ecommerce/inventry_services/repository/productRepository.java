package com.Ecommerce.inventry_services.repository;

import com.Ecommerce.inventry_services.entity.product;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface productRepository extends JpaRepository<product, Long> {

    product getProductById(long Id);

}


