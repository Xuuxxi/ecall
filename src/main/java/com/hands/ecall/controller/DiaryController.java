package com.hands.ecall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hands.ecall.common.BaseContext;
import com.hands.ecall.common.R;
import com.hands.ecall.pojo.Diary;
import com.hands.ecall.service.DiaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Resource
    private CacheManager cacheManager;

    @PostMapping("/add")
    public R<String> add(Diary diary){
        log.info("add diary and userId = " + BaseContext.getCurrentId());
        diaryService.save(diary);
        return R.success("添加成功！");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long>ids){
        log.info("deleting ids = " + ids);
        diaryService.removeByIds(ids);
        return R.success("删除成功！");
    }

    @PutMapping
    public R<String> update(@RequestBody Diary diary){
        log.info("updating diary... + " + diary.getId());
        diaryService.updateById(diary);
        return R.success("更新成功！");
    }

    /**
     * 年月日查询？？
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    @GetMapping("/page/{page}/{pageSize}")
    public R<Page> page(@PathVariable int page, @PathVariable int pageSize, String title, LocalDateTime stTime,LocalDateTime edTime) {
        log.info("日记名字/时间分页查询, page = {}, pageSize = {}", page, pageSize);
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Diary> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(title != null,Diary::getTitle,title);
        wrapper.between(stTime != null && edTime != null,Diary::getCreateTime,stTime,edTime);
        wrapper.orderByDesc(Diary::getUpdateTime);

        diaryService.page(pageInfo,wrapper);

        return R.success(pageInfo);
    }
}
