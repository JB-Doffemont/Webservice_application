package com.example.fisherfans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.example.fisherfans.entity")
public class FisherfansApplication {

    public static void main(String[] args) {
        SpringApplication.run(FisherfansApplication.class, args);
    }
}
