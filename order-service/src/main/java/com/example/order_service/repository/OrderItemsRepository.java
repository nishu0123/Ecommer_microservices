package com.example.order_service.repository;

import com.example.order_service.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItemsRepository, Long> {
     public List<OrderItems> getOrderItembyId(Integer id);
}
