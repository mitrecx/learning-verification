package com.example.boot01web01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class Boot01Web01Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot01Web01Application.class, args);
    }

}
