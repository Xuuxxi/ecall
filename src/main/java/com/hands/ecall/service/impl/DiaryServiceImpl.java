package com.hands.ecall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hands.ecall.mapper.DiaryMapper;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.service.DiaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {

    @Override
    public Page getPage(int page, int pageSize, String title, LocalDateTime stTime, LocalDateTime edTime) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(title != null,Diary::getTitle,title);
        wrapper.between(stTime != null && edTime != null,Diary::getCreateTime,stTime,edTime);
        wrapper.orderByDesc(Diary::getUpdateTime);

        this.page(pageInfo,wrapper);

        return pageInfo;
    }

    public Page getPage(int page, int pageSize, Long userId, LocalDateTime stTime, LocalDateTime edTime) {
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getUser_id,userId);
        wrapper.between(stTime != null && edTime != null,Diary::getCreateTime,stTime,edTime);
        wrapper.orderByDesc(Diary::getUpdateTime);

        this.page(pageInfo,wrapper);

        return pageInfo;
    }
}
