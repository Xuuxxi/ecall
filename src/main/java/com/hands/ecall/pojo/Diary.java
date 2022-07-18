package com.hands.ecall.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Xuuxxi
 * @Date: 2022/7/18
 */
@Data
@TableName("diary")
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long user_id;

    private String title;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
