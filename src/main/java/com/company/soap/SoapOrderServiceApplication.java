package com.company.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;

@SpringBootApplication(exclude = WebServicesAutoConfiguration.class)
public class SoapOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapOrderServiceApplication.class, args);
    }
}
