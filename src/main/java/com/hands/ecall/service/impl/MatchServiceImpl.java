package com.hands.ecall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hands.ecall.mapper.MatchMapper;
import com.hands.ecall.pojo.UserMatch;
import com.hands.ecall.service.MatchService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/8/26
 */
@Service
public class MatchServiceImpl extends ServiceImpl<MatchMapper, UserMatch> implements MatchService {
}
