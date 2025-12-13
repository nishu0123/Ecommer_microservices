package com.Ecommerce.inventry_services.services;
import com.Ecommerce.inventry_services.dto.OrderItemRequestDto;
import com.Ecommerce.inventry_services.dto.OrdersRequestDto;
import com.Ecommerce.inventry_services.dto.productDto;
import com.Ecommerce.inventry_services.entity.product;
import com.Ecommerce.inventry_services.repository.productRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.FileChannel;
import java.util.*;

import static java.rmi.server.LogStream.log;


@Service
@Slf4j
@RequiredArgsConstructor
//@NoArgsConstructor
public class productService {
    //here i have to implement the get all inventry
    private product productval;
    private final ModelMapper modelMapper;
    @Autowired
    private final productRepository productRepositryobj;


    public List<productDto> getAllInventry()
    {
        //here with the help of the product repository we will fetch all the product
        //and will return in the form of the list
        System.out.println("fetching the inventries");
        List<product> inventrories =  productRepositryobj.findAll();


        return inventrories.stream()
                .map(product ->modelMapper.map(product , productDto.class))
                .toList();

    }

    //method : get product by id

    public productDto getProductById(long Id)
    {

//        product product1 = productRepositry.getProductById(Id);
//        return modelMapper.map(product1  , productDto.class);
         // returning the product1 of the product type

        //

        //other logic


        //the below line has the error of the type conversion
//        Optional<product> inverntries =  productRepositryobj.getProductById(Id);

        Optional<product> inventries = Optional.ofNullable(productRepositryobj.getProductById(Id));


        //the below implementaiton can throw exception when there will be no product or can return null
        return modelMapper.map(inventries  , productDto.class);

        //the below implementation is more safer than above , this is the syntax for the optional

        //i have commented the below code because i was getting the error that below line is not reachable
        /*
        return inventries.map(item -> modelMapper.map(inventries , productDto.class))
                .orElseThrow(() -> new RuntimeException("Product not found"));

         */
    }

    //this is the service

    @Transactional // make this transactional so that either it will execute completly or it will be roll back
    public double reduceStock(OrdersRequestDto ordersRequestDto) {
        log("reducing stock ..." + "orderRequestDto = " + ordersRequestDto);
        double totalPrice = 0;
         //now i have details about the product whose order has been done
        for(OrderItemRequestDto items :  ordersRequestDto.getItems())
        {
            //now for each order-item we will check for the quantity and will calculate the total price
            Integer quantityOfOrderItem = items.getQuantity();
            Long productIdOfOrderItem = items.getProductId();

            //now fetch the data from the database
            product product = productRepositryobj.findById(productIdOfOrderItem).orElseThrow(
                    ()-> new RuntimeException("no product found"));//here map product to product Dto
            //now check if this product stock is available
            int availableStock = product.getStock();
            double price = product.getPrice();
            //calculate the total price
            totalPrice += price * quantityOfOrderItem;
            if(quantityOfOrderItem  <= availableStock )
            {
                //process the order and update the stock
                product.setStock(availableStock - quantityOfOrderItem);

                //save the updated stock of the product in the databse
                productRepositryobj.save(product);

            }else{
                throw new RuntimeException("out of stock");
            }
        }
        return totalPrice;
    }

    //here other implementation are also there , what we can do is that  , there is possibilities that
    //there will be the list of the product associated with one id so we will store in the list of id





}
