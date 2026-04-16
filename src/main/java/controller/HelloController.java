package com.stu.helloserver.controller;

import com.stu.helloserver.common.Result;
import com.stu.helloserver.dto.UserDTO;
import com.stu.helloserver.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class HelloController {

    @Resource
    private UserService userService;

    // 注册接口
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 登录接口
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 查询用户信息（需要登录）
    @GetMapping("/{id}")
    public Result<String> getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    // 测试接口
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello Server!");
    }
}