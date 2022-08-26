package com.hands.ecall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hands.ecall.common.BaseContext;
import com.hands.ecall.common.R;
import com.hands.ecall.dto.DiaryDto;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.pojo.Match;
import com.hands.ecall.service.DiaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@RestController
@Slf4j
@RequestMapping("/diary")
public class DiaryController {
    @Resource
    private DiaryService diaryService;

    @PostMapping("/add")
    public R<String> add(@RequestBody Diary diary) {
        log.info("add diary and userId = " + BaseContext.getCurrentId());
        diaryService.save(diary);
        return R.success("添加成功！");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("deleting ids = " + ids);
        diaryService.removeByIds(ids);
        return R.success("删除成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Diary diary) {
        log.info("updating diary... + " + diary.getId());
        diaryService.updateById(diary);
        return R.success("更新成功！");
    }

    @GetMapping("/page/{page}/{pageSize}")
    public R<Page> pageByName(@PathVariable int page, @PathVariable int pageSize, String title, LocalDateTime stTime, LocalDateTime edTime) {
        log.info("日记名字/时间分页查询, page = {}, pageSize = {}", page, pageSize);
        return R.success(diaryService.getPage(page, pageSize, title, stTime, edTime));
    }

    @GetMapping("/{userId}/{page}/{pageSize}")
    public R<Page> pageByUserId(@PathVariable Long userId, @PathVariable int page, @PathVariable int pageSize, LocalDateTime stTime, LocalDateTime edTime) {
        log.info("query by userId");
        return R.success(diaryService.getPage(page, pageSize, userId, stTime, edTime));
    }

//    @GetMapping("/match/{diaryId}")
//    public R<DiaryDto> getDto(@PathVariable Long diaryId){
//        log.info("match diary...");
//        DiaryDto dto = diaryService.getDto(diaryId);
//        return R.success(dto);
//    }

    @GetMapping("/similar/{diaryId}")
    public R<DiaryDto> getTheSame(@PathVariable Long diaryId){
        log.info("get the same diary");
        DiaryDto theSame = diaryService.getTheSame(diaryId);
        return R.success(theSame);
    }

    @GetMapping("/getWekInfo")
    public R<List> getWekInfo(){
        log.info("get week info");
        List<Double> wekInfo = diaryService.getWekInfo();
        return R.success(wekInfo);
    }

    @GetMapping("/getUserMatch/{userId}")
    public R<List> getUserMatch(@PathVariable Long userId){
        log.info("get user match");
        List<Match> userMatch = diaryService.getUserMatch(userId);
        return R.success(userMatch);
    }

    @GetMapping("/getSimilar/{diaryId}")
    public R<Diary> getSimilar(@PathVariable Long diaryId){
        log.info("find the most similar one");
        Diary similar = diaryService.getSimilar(diaryId);
        return R.success(similar);
    }
}
