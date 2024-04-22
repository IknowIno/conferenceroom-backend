package com.qianyier.common.dto;

import com.qianyier.entity.Apply;
import com.qianyier.entity.ApplyRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qianyier
 * @Description 申请时前后端交互的数据载体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private Apply apply;
    private ApplyRecord applyRecord;

}
