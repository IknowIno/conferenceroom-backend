package com.qianyier.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyier.entity.ApplyRecord;
import com.qianyier.mapper.ApplyRecordMapper;
import com.qianyier.service.ApplyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyRecordServiceImpl extends ServiceImpl<ApplyRecordMapper, ApplyRecord> implements ApplyRecordService {
    @Autowired
    ApplyRecordMapper applyRecordMapper;

    @Override
    public List<ApplyRecord> getApplyRecordByTea(String teaId) {
        return applyRecordMapper.getApplyRecordByTea(teaId);
    }
}
