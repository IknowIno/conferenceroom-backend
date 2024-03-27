package com.qianyier.service.impl;

import com.qianyier.entity.Admin;
import com.qianyier.mapper.AdminMapper;
import com.qianyier.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
