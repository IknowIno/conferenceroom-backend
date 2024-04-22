package com.qianyier.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qianyier.common.lang.Result;
import com.qianyier.entity.Apply;
import com.qianyier.entity.ApplyRecord;
import com.qianyier.service.ApplyService;
import com.qianyier.service.QuartzServie;
import com.qianyier.service.ApplyRecordService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author qianyier
 * @Description 会议室申请记录的api 供管理员、部门查询会议室的申请记录  对应到ConRApplyRecord实体类
 */
@RestController
@RequestMapping("/applyrecord")
public class ApplyRecordController {

    @Autowired
    ApplyRecordService applyRecordService;

    @Autowired
    ApplyService applyService;

    @Autowired
    QuartzServie quartzServie;

    @GetMapping("/getallapplyrecord")
    @RequiresRoles(value = "admin")
    public Result getAllApplyRecord() {
        List<ApplyRecord> applyRecordList = applyRecordService.list();
        return Result.succ(applyRecordList);
    }

    @GetMapping("/getapplyrecordbytea")
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    public Result getApplyRecordByTea(@RequestBody String teaId) {
        List<ApplyRecord> applyRecordList = applyRecordService.getApplyRecordByTea(teaId);
        return Result.succ(applyRecordList);
    }


}
