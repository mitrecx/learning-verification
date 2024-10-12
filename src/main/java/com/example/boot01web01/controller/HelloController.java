package com.example.boot01web01.controller;

import com.example.boot01web01.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/query")
    public String getUser() {
        System.out.println("开始 ...");
        emailService.sendEmail("Hello", "Hello World");
        System.out.println("结束 ...");
        return "GET-张三";
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
