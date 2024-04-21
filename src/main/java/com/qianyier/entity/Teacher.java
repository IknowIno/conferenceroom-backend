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

    private String teaId;
    private String teaName;
    private String password;
    private String teaEmail;
    private String college;
    private String major;
    private String position;
    private String researchDirection;
    private String scientificResults;
    private String role;

}
