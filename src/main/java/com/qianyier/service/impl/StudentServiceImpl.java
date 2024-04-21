package com.qianyier.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyier.entity.Student;
import com.qianyier.mapper.StudentMapper;
import com.qianyier.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Integer updateStuInfo(String stuId,String stuEmail,String selfIntroduction,String studyPlan){
        return studentMapper.updateStuInfo(stuId,stuEmail,selfIntroduction,studyPlan);
    }
}
