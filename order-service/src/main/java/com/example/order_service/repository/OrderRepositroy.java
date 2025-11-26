package com.example.order_service.repository;

import com.example.order_service.entity.Orders;

public interface OrderRepositroy extends JpaRepository<Orders, Long>{
    //here if i want to create our custom query then we can declare it in the interface
}
