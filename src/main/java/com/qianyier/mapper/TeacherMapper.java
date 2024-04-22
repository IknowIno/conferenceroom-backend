package com.qianyier.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyier.entity.Student;
import com.qianyier.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Update("UPDATE teacher SET teaEmail=#{teaEmail}, researchDirection=#{researchDirection}, scientificResults=#{scientificResults} WHERE teaId=#{teaId}")
    public Integer updateTeaInfo(String teaId,String teaEmail,String researchDirection,String scientificResults);

    @Select("select * from teacher where teaId=#{teaId}")
    public Teacher getTeacherById(String teaId);
}
