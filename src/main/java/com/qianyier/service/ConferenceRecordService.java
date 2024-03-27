package com.qianyier.service;

import com.qianyier.entity.ConferenceRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyier.entity.group.ConRApplyRecord;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qianyier
 */
public interface ConferenceRecordService extends IService<ConferenceRecord> {

    List<ConRApplyRecord> listByConditions(Integer auditState,
                                           String depName,Integer currentPage,Integer deleted);

    Integer getTotalByConditions( Integer auditState,
                                  String depName,Integer deleted);
}
