package com.qianyier.common.dto;

import com.qianyier.entity.Department;
import com.qianyier.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author qianyier
 * @Description 员工信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {
    private Employee employee;
    private Department department;
}
