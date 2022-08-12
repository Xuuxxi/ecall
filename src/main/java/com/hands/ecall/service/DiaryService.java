package com.hands.ecall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hands.ecall.dto.DiaryDto;
import com.hands.ecall.pojo.Diary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
public interface DiaryService extends IService<Diary> {
    public Page getPage(int page, int pageSize, String title, LocalDateTime stTime, LocalDateTime edTime);
    public Page getPage(int page, int pageSize, Long userId, LocalDateTime stTime, LocalDateTime edTime);

    public DiaryDto getDto(Long diaryId);

    public DiaryDto getTheSame(Long diaryId);
}
