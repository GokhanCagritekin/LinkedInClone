package com.trendyolbootcamp.linkedinclonejob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class LinkedinCloneJobApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello Job";
    }

    public static void main(String[] args) {
        SpringApplication.run(LinkedinCloneJobApplication.class, args);
    }

}

//