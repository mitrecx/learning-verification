package com.example.boot01web01.controller;

import com.example.boot01web01.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
@PropertySource(value = "classpath:hello.properties")
public class HelloController {
    @Autowired
    private EmailService emailService;

    @Value("${controller.greeting}")
    private String greeting;

    @GetMapping("/query")
    public String query() {
        System.out.println("开始 ...");
        emailService.sendEmail("Hello", "Hello World");
        System.out.println("结束 ...");
        System.out.println("greeting:" + greeting);
        return greeting;
    }

    @PostMapping("/save")
    public String saveUser() {
        return "POST-张三";
    }


    @PutMapping(value = "/update")
    public String putUser() {
        return "PUT-张三";
    }

    @DeleteMapping("/delete")
    public String deleteUser() {
        return "DELETE-张三";
    }

}
