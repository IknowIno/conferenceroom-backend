//package com.qianyier.quartz;
//
//import com.qianyier.service.EmailService;
//import lombok.SneakyThrows;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * @author qianyier
// * @Description
// */
//@Component
//public class EmailJobDetail implements Job {
//
//    @Autowired
//    EmailService emailService;
//
//    @SneakyThrows
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//
//        Map<String,Object> info = (Map<String, Object>) context.getJobDetail().getJobDataMap().get("info");
//
//
//        //表示发的是申请的邮件
//        if(info.get("applyReason")== null){
//           emailService.sendResultMail((String)info.get("teaName"),"关于申请的邮件",info);
//        }else{
//           emailService.sendNotifyMail((String)info.get("teaName"),"关于申请的邮件",info);
//        }
//    }
//}
