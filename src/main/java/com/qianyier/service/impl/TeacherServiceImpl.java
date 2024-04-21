package com.qianyier.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qianyier.entity.Teacher;
import com.qianyier.mapper.TeacherMapper;
import com.qianyier.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Integer updateTeaInfo(String teaId, String teaEmail, String researchDirection, String scientificResults) {
        return teacherMapper.updateTeaInfo(teaId, teaEmail, researchDirection, scientificResults);
    }
}
