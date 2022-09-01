package com.hands.ecall.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Xuuxxi
 * @Date: 2022/8/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_match")
public class UserMatch implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long diaryId;

    private Long matchUser;
}
