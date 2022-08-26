package com.hands.ecall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hands.ecall.dto.DiaryDto;
import com.hands.ecall.mapper.DiaryMapper;
import com.hands.ecall.mapper.MatchMapper;
import com.hands.ecall.mapper.UserMapper;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.pojo.Match;
import com.hands.ecall.service.DiaryService;
import com.hands.ecall.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {
    @Resource
    MatchMapper matchMapper;

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

//    public DiaryDto getDto(Long diaryId){
//        Diary diary = this.getById(diaryId);
//        Double curMood = diary.getMood();
//        DiaryDto diaryDto = new DiaryDto();
//
//        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
//        wrapper.ne(Diary::getUserId,diary.getUserId());
//        wrapper.between(Diary::getMood,curMood - 1,curMood + 1);
//        List<Diary> diaryList = this.list(wrapper);
//
//        if(diaryList != null){
//            Diary ans = diaryList.get(new Random().nextInt(diaryList.size()));
//            BeanUtils.copyProperties(diary,diaryDto);
//            diaryDto.setMatchId(ans.getUserId());
//        }
//
//        return diaryDto;
//    }

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
            BeanUtils.copyProperties(curDiary,diaryDto);
            diaryDto.setMatchUserId(diary.getUserId());
            diaryDto.setMatchDiaryId(diary.getId());
            diaryDto.setMatchMood(diary.getMood());

            Match match = new Match();
            match.setDiaryId(curDiary.getId());
            match.setUserId(diary.getUserId());
            matchMapper.insert(match);
        }

        return diaryDto;
    }

    public List<Double> getWekInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime pre = now.plusDays(-7L);

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getUserId,userId);
        wrapper.between(Diary::getCreateTime,now,pre);

        List<Diary> list = this.list(wrapper);

        //前面七个是前七天的数值，第八个是总和
        Double[] tmp = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

        for(Diary i : list){
            tmp[now.getDayOfYear() - i.getCreateTime().getDayOfYear()] = i.getMood();
            tmp[7] += i.getMood();
        }

        return new ArrayList<>(Arrays.asList(tmp));
    }

    @Override
    public List<Match> getUserMatch(Long userId) {
        QueryWrapper<Match> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        return matchMapper.selectList(wrapper);
    }

    public Diary getSimilar(Long diaryId) {
        UserDetailsImpl loginUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getUser().getId();
        Diary curDiary = this.getById(diaryId);

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Diary::getUserId,userId);
        wrapper.orderByDesc(Diary::getMood);
        List<Diary> list = this.list(wrapper);

        Double tmp = 10.0;
        Diary res = null;

        for(Diary i : list){
            Double mood = Math.abs(i.getMood() - curDiary.getMood());

            if(mood < tmp){
                tmp = mood;
                res = i;
            }else break;
        }

        return res;
    }
}
