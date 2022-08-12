package com.hands.ecall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hands.ecall.dto.DiaryDto;
import com.hands.ecall.mapper.DiaryMapper;
import com.hands.ecall.mapper.UserMapper;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.service.DiaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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
        wrapper.eq(Diary::getUserId,userId);
        wrapper.between(stTime != null && edTime != null,Diary::getCreateTime,stTime,edTime);
        wrapper.orderByDesc(Diary::getUpdateTime);

        this.page(pageInfo,wrapper);

        return pageInfo;
    }

    public DiaryDto getDto(Long diaryId){
        Diary diary = this.getById(diaryId);
        Double curMood = diary.getMood();
        DiaryDto diaryDto = new DiaryDto();

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Diary::getUserId,diary.getUserId());
        wrapper.between(Diary::getMood,curMood - 1,curMood + 1);
        List<Diary> diaryList = this.list(wrapper);

        if(diaryList != null){
            Diary ans = diaryList.get(new Random().nextInt(diaryList.size()));
            BeanUtils.copyProperties(diary,diaryDto);
            diaryDto.setMatchId(ans.getUserId());
        }

        return diaryDto;
    }

    public DiaryDto getTheSame(Long diaryId){
        DiaryDto diaryDto = new DiaryDto();
        Diary curDiary = this.getById(diaryId);
        Double curMood = curDiary.getMood();

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Diary::getId,diaryId);
        wrapper.between(Diary::getMood,0,curMood);
        wrapper.orderByDesc(Diary::getMood);
        List<Diary> diaryList = this.list(wrapper);

        if(diaryList != null){
            Diary diary = diaryList.get(0);
            BeanUtils.copyProperties(diary,diaryDto);
            diaryDto.setRate(diaryDto.getMood() / curMood);
        }

        return diaryDto;
    }
}
