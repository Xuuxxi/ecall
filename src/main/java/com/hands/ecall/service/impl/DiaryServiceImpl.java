package com.hands.ecall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hands.ecall.mapper.DiaryMapper;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.service.DiaryService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {
}
