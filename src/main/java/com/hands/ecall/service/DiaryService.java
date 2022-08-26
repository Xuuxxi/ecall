package com.hands.ecall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hands.ecall.dto.DiaryDto;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.pojo.UserMatch;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
public interface DiaryService extends IService<Diary> {
    public Page getPage(int page, int pageSize, String title, LocalDateTime stTime, LocalDateTime edTime);
    public Page getPage(int page, int pageSize, Long userId, LocalDateTime stTime, LocalDateTime edTime);

    //public DiaryDto getDto(Long diaryId);

    public DiaryDto getTheSame(Long diaryId);

    public List<Double> getWekInfo();

    public List<UserMatch> getUserMatch(Long userId);

    public Diary getSimilar(Long diaryId);
}
