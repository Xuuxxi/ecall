package com.hands.ecall.dto;

import com.hands.ecall.pojo.Diary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Xuuxxi
 * @Date: 2022/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDto extends Diary {
    private Long matchUserId;

    private Long matchDiaryId;

    private Double matchMood;
}
