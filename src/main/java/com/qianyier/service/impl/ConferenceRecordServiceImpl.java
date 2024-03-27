package com.qianyier.service.impl;

import com.qianyier.entity.ConferenceRecord;
import com.qianyier.entity.group.ConRApplyRecord;
import com.qianyier.mapper.ConferenceRecordMapper;
import com.qianyier.service.ConferenceRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qianyier
 */
@Service
public class ConferenceRecordServiceImpl extends ServiceImpl<ConferenceRecordMapper, ConferenceRecord> implements ConferenceRecordService {

    @Autowired
    ConferenceRecordMapper conferenceRecordMapper;

    @Override
    public List<ConRApplyRecord> listByConditions( Integer auditState,
                                                  String depName,Integer currentPage,Integer deleted){

        //index从0开始
        Integer startIndex = (currentPage-1)*7; //一页7条数据

        return conferenceRecordMapper.listByConditions(auditState,depName,startIndex,deleted);
    }

    @Override
    public Integer getTotalByConditions(Integer auditState, String depName,Integer deleted) {
        return conferenceRecordMapper.getTotalByConditions(auditState,depName,deleted);
    }

}
