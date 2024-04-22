package com.qianyier.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qianyier.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Update("UPDATE student SET stuEmail=#{stuEmail}, selfIntroduction=#{selfIntroduction}, studyPlan=#{studyPlan} WHERE stuId=#{stuId}")
    public Integer updateStuInfo(String stuId,String stuEmail,String selfIntroduction,String studyPlan);

    @Update("UPDATE student SET tutorName=#{tutorName}")
    public Integer updateTutorName(String stuId,String tutorName);

    @Select("select * from student where stuId=#{stuId}")
    public Student getStudentById(String stuId);

}
