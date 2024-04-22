package com.qianyier.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyier.entity.Apply;
import com.qianyier.entity.ApplyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApplyRecordMapper extends BaseMapper<ApplyRecord> {
    @Select("select * from applyrecord where teaId = #{teaId}")
    public List<ApplyRecord> getApplyRecordByTea(String teaId);

}
