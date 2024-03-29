package com.hands.ecall.controller;

import com.hands.ecall.common.R;
import com.hands.ecall.pojo.User;
import com.hands.ecall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<String> login(@RequestBody User user){
        log.info("user : " + user + "login...");
        String res = userService.login(user);
        return R.success(res);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody User user){
        log.info("user register");
        String register = userService.register(user);
        return R.success(register);
    }

    @GetMapping("/info")
    public R<User> getUserInfo(){
        log.info("current user");
        User info = userService.getInfo();
        return R.success(info);
    }

    @PutMapping
    public R<String> update(@RequestBody User user) {
        log.info("update user...");
        userService.updateById(user);
        return R.success("update success...");
    }

    @GetMapping("/{userId}")
    public R<User> query(@PathVariable Long userId) {
        log.info("query user...");
        User user = userService.getById(userId);
        return R.success(user);
    }
}
