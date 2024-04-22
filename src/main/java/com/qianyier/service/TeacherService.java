package com.qianyier.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qianyier.entity.Apply;
import com.qianyier.entity.Teacher;

public interface TeacherService extends IService<Teacher> {

    public Integer updateTeaInfo(String teaId,String teaEmail,String researchDirection,String scientificResults);

    public Teacher getTeacherById(String teaId);

}
