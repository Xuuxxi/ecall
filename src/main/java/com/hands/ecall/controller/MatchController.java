package com.hands.ecall.controller;

import com.hands.ecall.common.R;
import com.hands.ecall.pojo.UserMatch;
import com.hands.ecall.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PostMapping("/add")
    public R<String> add(@RequestBody UserMatch match){
        log.info("add match info");
        matchService.save(match);
        return R.success("add success");
    }
}
