package com.hands.ecall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hands.ecall.pojo.UserMatch;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Xuuxxi
 * @Date: 2022/8/26
 */

@Mapper
public interface MatchMapper extends BaseMapper<UserMatch> {
}
