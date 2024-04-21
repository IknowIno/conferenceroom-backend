package com.qianyier.service.impl;

import com.qianyier.entity.Apply;
import com.qianyier.mapper.ApplyMapper;
import com.qianyier.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qianyier
 */
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    @Autowired
    public ApplyMapper applyMapper;

    @Override
    public List<Apply> getApplyByTeaAndState(String teaId, Integer auditState) {
        return applyMapper.getApplyByTeaAndState(teaId,auditState);
    }

    @Override
    public List<Apply> getAllApplyByTea(String teaId) {
        return applyMapper.getAllApplyByTea(teaId);
    }
}
