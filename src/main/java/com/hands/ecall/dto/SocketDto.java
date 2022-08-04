package com.hands.ecall.dto;


import com.hands.ecall.pojo.SocketData;
import lombok.Data;

import java.util.List;

/**
 * @Author: Xuuxxi
 * @Date: 2022/8/4
 */

@Data
public class SocketDto {
    private List<SocketData> dataList;
}
