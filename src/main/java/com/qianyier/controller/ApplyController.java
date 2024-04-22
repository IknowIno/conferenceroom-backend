package com.qianyier.controller;


import com.qianyier.common.exception.SystemException;
import com.qianyier.common.lang.Result;
import com.qianyier.entity.Apply;

import com.qianyier.entity.ApplyRecord;
import com.qianyier.entity.Student;
import com.qianyier.entity.Teacher;
import com.qianyier.service.*;

import org.apache.shiro.authz.annotation.Logical;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;


/**
 * @author qianyier
 * @Description 申请会议室时所需要的api
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {


    @Autowired
    ApplyService applyService;

    @Autowired
    ApplyRecordService applyRecordService;

//    @Autowired
//    QuartzServie quartzServie;

    @Autowired
    EmailService emailService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;


    /**
     * 添加一个学生发起的申请
     *
     * @return
     */
    @PostMapping("/add")
    @RequiresRoles(value = {"student", "admin"}, logical = Logical.OR)
    public Result add(@RequestBody Apply apply) throws SystemException {
        if (applyService.getApplyByStu(apply.getStuId()) != null) {
            return Result.fail("该学生已向导师发送申请");
        } else {
            Student studentById = studentService.getStudentById(apply.getStuId());
            Teacher teacherById = teacherService.getTeacherById(apply.getTeaId());
            applyService.save(apply);
            //延迟时间给导师发送邮件
            Map<String, Object> map1 = new HashMap<>();
            map1.put("stuName",studentById.getStuName());
            map1.put("stuEmail",studentById.getStuEmail());
            map1.put("applyReason",apply.getApplyReason());
//            Long time = System.currentTimeMillis();
//            time += 10*1000;
            emailService.sendNotifyMail(teacherById.getTeaEmail(),"导师通知邮件",map1);
            //quartzServie.startJob(time, UUID.randomUUID().toString(), EmailJobDetail.class, map1);
            return Result.succ("");
        }
    }


    //取消一个申请
    @DeleteMapping("/del")
    @RequiresRoles(value = {"student", "admin"}, logical = Logical.OR)
    public Result delete(@RequestBody String stuId) {
        applyService.removeById(stuId);
        return Result.succ("");
    }

    /**
     * 查看一个导师收到的所有申请,根据导师id和状态查询
     */
    @GetMapping("/getapplybytea/{teaId}")
    @RequiresRoles(value = {"admin", "teacher"}, logical = Logical.OR)
    public Result getByIdAndState(@PathVariable("teaId") String teaId, @RequestBody Integer auditState) {
        List<Apply> applyList = applyService.getApplyByTeaAndState(teaId, auditState);

        return Result.succ(applyList);
    }

    /**
     * 查看一个导师收到的所有申请,无论是否通过
     */
    @GetMapping("/getallapplybytea/{teaId}")
    @RequiresRoles(value = {"admin", "teacher"}, logical = Logical.OR)
    public Result getAllById(@PathVariable("teaId") String teaId) {
        List<Apply> applyList = applyService.getAllApplyByTea(teaId);

        return Result.succ(applyList);
    }

    @PutMapping("/changeauditstate")
    @RequiresRoles(value = {"admin", "teacher"}, logical = Logical.OR)
    public Result changeAuditState(@RequestBody String applyId,@RequestBody Integer auditState) throws ParseException, SystemException {
        applyService.changeAuditState(applyId,auditState);
        if (auditState == 1) {
            Apply apply = applyService.getById(applyId);
            ApplyRecord applyRecord = new ApplyRecord();
            Student studentById = studentService.getStudentById(apply.getStuId());
            Teacher teacherById = teacherService.getTeacherById(apply.getTeaId());
            applyRecord.setStuName(studentById.getStuName());
            applyRecord.setTeaName(teacherById.getTeaName());
            applyRecord.setTeaId(apply.getTeaId());
            applyRecord.setStuId(apply.getStuId());
            applyRecordService.save(applyRecord);
            studentService.updateTutorName(studentById.getStuId(),teacherById.getTeaName());
            //发送邮件
            Map<String, Object> map1 = new HashMap<>();
            map1.put("teaName",teacherById.getTeaName());
            map1.put("teaEmail",teacherById.getTeaEmail());
            emailService.sendResultMail(studentById.getStuEmail(),"学生通知邮件",map1);

//            Long time = System.currentTimeMillis();
//            time += 10*1000;
            //quartzServie.startJob(time, UUID.randomUUID().toString(), EmailJobDetail.class, map1);
        }
        return Result.succ("");
    }


    @PutMapping("/rejectApply")
    @RequiresRoles(value = {"admin", "teacher"}, logical = Logical.OR)
    public Result rejectApply(@RequestBody String applyId,@RequestBody String rejectReason) throws ParseException, SystemException {
        applyService.changeAuditState(applyId,2);

        Apply apply = applyService.getById(applyId);
        Teacher teacherById = teacherService.getTeacherById(apply.getTeaId());
        Student studentById = studentService.getStudentById(apply.getStuId());

        //发送邮件
        Map<String, Object> map1 = new HashMap<>();
        map1.put("rejectReason",rejectReason);
        map1.put("teaName",teacherById.getTeaName());
        map1.put("teaEmail",teacherById.getTeaEmail());
        emailService.sendResultMail(studentById.getStuEmail(),"学生通知邮件",map1);

//        Long time = System.currentTimeMillis();
//        time += 10*1000;
        //quartzServie.startJob(time, UUID.randomUUID().toString(), EmailJobDetail.class, map1);

        return Result.succ("");

    }

}
