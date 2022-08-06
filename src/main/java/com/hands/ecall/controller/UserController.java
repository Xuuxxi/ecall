package com.hands.ecall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hands.ecall.common.BaseContext;
import com.hands.ecall.common.R;
import com.hands.ecall.pojo.User;
import com.hands.ecall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public R<Long> login(HttpServletRequest request, @RequestBody User user) {
        log.info("用户 " + user.getName() + " 登陆");
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, user.getName());
        User user1 = userService.getOne(wrapper);

        if (user1 == null) {
            return R.error("登录失败，不存在该用户");
        }

        if (!user1.getPassword().equals(password)) {
            return R.error("用户密码错误");
        }

        if (user1.getStatus() == 1) {
            return R.error("该账户已被禁用");
        }

        log.info("登陆成功");
        BaseContext.setCurrentId(user1.getId());
        request.getSession().setAttribute("user", user1.getId());

        return R.success(user1.getId());
    }

    @PostMapping("/logout")
    public R<String> logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        log.info("注销成功");
        return R.success("注销成功");
    }

    /**
     * 注册接口
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody User user) {
        log.info("新用户注册 {}", user.toString());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, user.getName());
        User user1 = userService.getOne(wrapper);
        if (user1 != null) return R.error("该用户已经存在");

        String pwd = DigestUtils.md5DigestAsHex("123456".getBytes());
        if (user.getPassword() != null) pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        user.setPassword(pwd);
        user.setStatus(0);

        userService.save(user);
        return R.success("用户注册成功！");
    }

    /**
     * 更新用户接口
     *
     * @param user
     * @return
     */
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

    @GetMapping("/login/{username}/{password}")
    public R<Long> loginForTest(HttpServletRequest request, @PathVariable String username, @PathVariable String password) {
        log.info("用户 " + username + " 登陆");

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, username);
        User user1 = userService.getOne(wrapper);

        if (user1 == null) {
            return R.error("登录失败，不存在该用户");
        }

        if (!user1.getPassword().equals(password)) {
            return R.error("用户密码错误");
        }

        if (user1.getStatus() == 1) {
            return R.error("该账户已被禁用");
        }

        log.info("登陆成功");
        BaseContext.setCurrentId(user1.getId());
        request.getSession().setAttribute("user", user1.getId());

        return R.success(user1.getId());
    }
}
