package com.qianyier.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String stuId;
    private String stuName;
    private String password;
    private String stuEmail;
    private String grade;
    private Integer gender;
    private String major;
    private Double gradePoint;
    private String selfIntroduction;
    private String studyPlan;
    private String tutorName;
    private String role;
//      `stuId` varchar(20) NOT NULL COMMENT '学号',
//            `stuName` varchar(30) NOT NULL,
//  `password` varchar(30) NOT NULL,
//  `stuEmail` varchar(50) NOT NULL,
//  `grade` varchar(20) NOT NULL,
//  `gender` int(2) NOT NULL,
//  `major` varchar(20) NOT NULL,
//  `gradePoint` double ,
//            `selfIntroduction` varchar(255) NOT NULL ,
//  `studyPlan` varchar(255) NOT NULL,
//  `tutorName` varchar(20) DEFAULT '暂无',
//            `role` varchar(20) DEFAULT 'student',
}
