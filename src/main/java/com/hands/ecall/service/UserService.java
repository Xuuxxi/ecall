package com.hands.ecall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hands.ecall.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
public interface UserService extends IService<User> {
    public String login(User user);
    public User getInfo();
    public String register(User user);
}
