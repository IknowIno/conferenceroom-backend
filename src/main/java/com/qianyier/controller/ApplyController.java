package com.qianyier.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qianyier.common.dto.Record;
import com.qianyier.common.lang.Result;
import com.qianyier.entity.Apply;

import com.qianyier.service.ApplyService;

import org.apache.shiro.authz.annotation.Logical;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * @author qianyier
 * @Description 申请会议室时所需要的api
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {


    @Autowired
    ApplyService applyService;

//    @Autowired
//    ConferenceRecordService conferenceRecordService;

//    @Autowired
//    ConferenceRoomService conferenceRoomService;


    /**
     * {
     * {
     * depId
     * roomId
     * }
     * {
     * statrTime
     * endTime
     * theme
     * digest
     * personCount
     * <p>
     * }
     * }
     * <p>
     * <p>
     * 添加一个学生发起的申请
     *
     * @return
     */
    @PostMapping("/add")
    @RequiresRoles(value = {"student", "admin"}, logical = Logical.OR)
    public Result add(@RequestBody Apply apply) {
        if (applyService.getApplyByStu(apply.getStuId()) != null) {
            return Result.fail("该学生已向导师发送申请");
        } else {
            applyService.save(apply);
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
    public Result changeAuditState(@RequestBody String applyId,@RequestBody Integer auditState) throws ParseException {
        applyService.changeAuditState(applyId,auditState);
        return Result.succ("");
    }


    /**
     * 管理员紧急申请
     * @param record
     * @return
     */
//    @PostMapping("/addbyadmin")
//    @RequiresRoles(value = "admin")
//    public Result addByAdmin(@RequestBody Record record){
//
//        System.out.println(record);
//
//        Apply apply = record.getApply();
//        ConferenceRecord conferenceRecord = record.getConferenceRecord();
//        applyService.save(apply);
//
//        System.out.println(apply);
//
//        //直接通过  因为时间冲突在前端判断过了  save会将id回显给apply
//        applyService.update(new UpdateWrapper<Apply>().eq("apply_id",apply.getApplyId())
//        .set("audit_state",1));
//
//        //要设置外键
//        conferenceRecord.setApplyId(apply.getApplyId());
//        conferenceRecordService.save(conferenceRecord);
//
//        return Result.succ("");
//
//    }


    /**
     * 查出来如果是List不为空 则证明有冲突
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
//    @GetMapping("/searchtimeconflict/{roomId}/{startTime}/{endTime}")
//    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
//    public Result searchtimeconflict(@PathVariable("roomId") Integer roomId,
//                                     @PathVariable("startTime")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
//                                     @PathVariable("endTime")  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime endTime){
//
//
//        List<Integer> listApplyId = applyService.searchTimeConflict(roomId,startTime,endTime);
//
//        if(listApplyId.size()>0){
//            return Result.succ("0");
//        }
//        return Result.succ("1");
//    }
//
//
//    /**
//     * 根据id查一个会议室  供页面申请会议室时 点击申请 就是到后台拿到这个会议室的所有数据
//     */
//    @GetMapping("/getconfroom/{id}")
//    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
//    public Result getById(@PathVariable("id") Integer id){
//        ConferenceRoom byId = conferenceRoomService.getById(id);
//        return Result.succ(byId);
//    }


}
