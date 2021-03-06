package com.trendyolbootcamp.linkedinclonecandidate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class LinkedinCloneCandidateApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello Candidate";
    }

    public static void main(String[] args) {
        SpringApplication.run(LinkedinCloneCandidateApplication.class, args);
    }

}
