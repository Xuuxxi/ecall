package com.hands.ecall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EcallApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcallApplication.class, args);
    }

}
