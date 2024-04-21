package com.qianyier.mapper;


import com.qianyier.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

}
