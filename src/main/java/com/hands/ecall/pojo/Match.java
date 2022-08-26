package com.hands.ecall.pojo;

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
public class Match implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long diaryId;
}
