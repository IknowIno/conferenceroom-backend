package com.qianyier.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyier.entity.Apply;
import com.qianyier.entity.Student;

public interface StudentService extends IService<Student> {
    public Integer updateStuInfo(String stuId,String stuEmail,String selfIntroduction,String studyPlan);

    public Integer updateTutorName(String stuId,String tutorName);

    public Student getStudentById(String stuId);

}
