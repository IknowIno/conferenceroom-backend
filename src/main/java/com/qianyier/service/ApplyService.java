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

    List<Integer> searchTimeConflict(Integer roomId, LocalDateTime startTime, LocalDateTime endTime);
}
