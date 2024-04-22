package com.qianyier.service;

import com.qianyier.entity.ApplyRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qianyier
 */
public interface ApplyRecordService extends IService<ApplyRecord> {

    public List<ApplyRecord> getApplyRecordByTea(String teaId);

}


