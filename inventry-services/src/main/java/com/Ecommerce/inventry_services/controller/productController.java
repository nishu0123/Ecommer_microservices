package com.Ecommerce.inventry_services.controller;

import com.Ecommerce.inventry_services.dto.productDto;
import com.Ecommerce.inventry_services.entity.product;
import com.Ecommerce.inventry_services.services.productService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class productController {
    private final productService productService;
    private ModelMapper modelmapper;

    @GetMapping
    public ResponseEntity<List<productDto>> getAllInventry()
    {
        List<productDto> productDtoList = productService.getAllInventry();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<productDto> getProductById(@PathVariable long id)
    {
        productDto inventories = productService.getProductById(id);
        return ResponseEntity.ok(inventories);
//        product productval = productService.getProductById(id);
//        productDto productDtoval = modelmapper.map(product , productDto.class);
//        return productDtoval;
    }

    

}
