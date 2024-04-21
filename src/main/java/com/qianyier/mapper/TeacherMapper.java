package com.qianyier.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyier.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
}
