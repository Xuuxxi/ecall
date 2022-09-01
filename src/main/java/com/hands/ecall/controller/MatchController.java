package com.hands.ecall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hands.ecall.common.R;
import com.hands.ecall.pojo.UserMatch;
import com.hands.ecall.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/8/26
 */
@RestController
@RequestMapping("/match")
@Slf4j
public class MatchController {
    @Resource
    private MatchService matchService;

    @GetMapping("/find/{userId}")
    public R<List<UserMatch>> find(@PathVariable Long userId){
        log.info("user match finding");
        LambdaQueryWrapper<UserMatch> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserMatch::getUserId,userId);

        List<UserMatch> list = matchService.list(wrapper);
        return R.success(list);
    }
}
