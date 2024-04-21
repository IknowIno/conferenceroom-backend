package com.qianyier.service;

import com.qianyier.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qianyier
 */
public interface ApplyService extends IService<Apply> {

    public List<Apply> getApplyByTeaAndState(String teaId,Integer auditState);

    public List<Apply> getAllApplyByTea(String teaId);
}
