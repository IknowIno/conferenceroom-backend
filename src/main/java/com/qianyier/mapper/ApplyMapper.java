package com.qianyier.mapper;


import com.qianyier.entity.Apply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
@Repository
public interface ApplyMapper extends BaseMapper<Apply> {

    /**
     * 自定义的查询语句  查询申请是否时间冲突
     *
     */

    List<Integer> searchTimeConflict(@Param("roomId") Integer roomId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
