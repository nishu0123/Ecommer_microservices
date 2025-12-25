package com.Ecommerce.user_service.user_service.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final KafkaTemplate<String , String> kafkaTemplate;

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_USER_RANDOM_TOPIC;

    @PostMapping("/{message}")
    public ResponseEntity<String> SendUserMessage(@PathVariable String message)
    {
        //here i will be getting the message in the request body
        //let see how we can do the things
        for(int i = 0; i<10000; i++)
        {
            kafkaTemplate.send(KAFKA_USER_RANDOM_TOPIC ,""+i%4  , message + "  " +  i);
        }

        return ResponseEntity.ok("message queued");

    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody String userInfo)
    {
        //here we have to create the users and then give the response as a
        //string to create the response body

        //before giving the resposne make sure that message have been queued

        return ResponseEntity.ok("use-data received " + userInfo);
    }

    @GetMapping("/")
    public String greetUsers()
    {
        return  "Hello users";
    }


}
