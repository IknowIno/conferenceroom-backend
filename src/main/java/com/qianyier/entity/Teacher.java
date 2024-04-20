package com.qianyier.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Teacher implements Serializable {
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

}
