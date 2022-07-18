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
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @TableField(value = "password")
    private String password;

    private String phone;

    private Integer sex;

    private String avatar;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
