package com.qianyier.controller;

import com.qianyier.quartz.EmailJobDetail;
import com.qianyier.service.QuartzServie;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author qianyier
 * @Description
 */

@RestController
public class testController {

    @Autowired
    QuartzServie quartzServie;


    @GetMapping("/testshiro")
    @RequiresRoles("admin")
    public void test(){
        System.out.println("1111111");
    }


    /**
     * 测试定时任务得启动项目 不能用简单的测试
     */
    @GetMapping("testquartz")
    public void test1(){
        long time = System.currentTimeMillis();
        System.out.println(time);
        time+=60*1000*2;
        Map<String, Object> content = new HashMap<>();
        content.put("roomFloor","11");
        content.put("roomNo","302");
        content.put("startTime","2024-01-25 15:02:00");
        content.put("endTime","2024-01-26 15:02:00");
        content.put("result","申请通过");
        content.put("theme","asdasda");
        content.put("applyTime","2024-01-25 15:02:00");
        content.put("email","1984196795@qq.com");
        quartzServie.startJob(time,"121",EmailJobDetail.class,content);
    }
}
