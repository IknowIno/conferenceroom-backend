package com.qianyier.service.impl;

import com.qianyier.entity.Employee;
import com.qianyier.mapper.EmployeeMapper;
import com.qianyier.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qianyier
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
