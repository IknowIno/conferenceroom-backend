package com.qianyier.mapper;


import com.qianyier.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qianyier
 */
@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {
    @Select("select * from apply where teaId = #{teaId} and auditState = #{auditState}")
    public List<Apply> getApplyByTeaAndState(String teaId,Integer auditState);

    @Select("select * from apply where teaId = #{teaId}")
    public List<Apply> getAllApplyByTea(String teaId);

    @Select("select * from apply where stuId = #{stuId}")
    public Apply getApplyByStu(String stuId);

    @Update("UPDATE apply SET auditState=#{auditState} WHERE applyId=#{applyId}")
    public Integer changeAuditState(String applyId,Integer auditState);

}
