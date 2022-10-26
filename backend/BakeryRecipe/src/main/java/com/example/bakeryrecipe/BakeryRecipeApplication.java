package com.example.bakeryrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class BakeryRecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryRecipeApplication.class, args);
    }

}
