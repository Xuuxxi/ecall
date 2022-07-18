package com.hands.ecall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hands.ecall.mapper.UserMapper;
import com.hands.ecall.pojo.User;
import com.hands.ecall.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
