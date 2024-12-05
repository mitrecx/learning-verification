package com.example.boot01web01.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Api(tags = "用户管理") // 定义分组名称
public class UserController {

    @ApiOperation("获取用户详情")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return new User(id, "张三");
    }

    @ApiOperation("创建新用户")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }

    @ApiModel(description = "用户信息模型")
    @Getter
    static class User {
        @ApiModelProperty("用户 ID")
        private Long id;
        @ApiModelProperty("用户名称")
        private String name;

        // 构造器、Getter 和 Setter
        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}