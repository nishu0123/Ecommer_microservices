package com.Ecommerce.inventry_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InventryServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventryServicesApplication.class, args);
	}

}
