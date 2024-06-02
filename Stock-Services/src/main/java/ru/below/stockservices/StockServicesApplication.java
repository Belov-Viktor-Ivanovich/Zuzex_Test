package ru.below.stockservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StockServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockServicesApplication.class, args);
    }

}
