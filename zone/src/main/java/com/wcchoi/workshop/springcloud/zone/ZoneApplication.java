package com.wcchoi.workshop.springcloud.zone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ZoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZoneApplication.class, args);
    }

}
