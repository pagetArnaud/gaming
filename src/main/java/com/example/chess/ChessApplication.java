package com.example.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessApplication {

    public static void main(String[] args) {
        System.out.println("SYSTEM STARTING");
        SpringApplication.run(ChessApplication.class, args);
    }
}
