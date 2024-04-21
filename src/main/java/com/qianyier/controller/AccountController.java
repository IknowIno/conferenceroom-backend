package com.qianyier.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qianyier.common.dto.LoginDto;
import com.qianyier.common.lang.Result;

import com.qianyier.entity.Admin;
import com.qianyier.entity.Student;
import com.qianyier.entity.Teacher;
import com.qianyier.service.AdminService;
import com.qianyier.service.StudentService;
import com.qianyier.service.TeacherService;
import com.qianyier.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author qianyier
 * @Description
 */
@RestController
public class AccountController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    AdminService adminService;

    @Autowired
    StudentService studentService;

    @Autowired
    JwtUtils jwtUtils;


    /**
     * 刷新token  flag判断是不是要获取用户信息
     *
     * @param
     * @return
     */
    @GetMapping("/refreshToken/{flag}")
    public Result getInfo(@PathVariable("flag") boolean flag, HttpServletRequest request, HttpServletResponse response) {
        //解析token
        String jwt = request.getHeader("Authorization");
        Claims claimByToken = jwtUtils.getClaimByToken(jwt);
        if (claimByToken == null) {
            throw new ExpiredCredentialsException("认证无效 请重新登陆");
        }
        //throw new TestException("模拟token");
        String id = claimByToken.getSubject();

        //刷新token 生成新的 写在响应头里
        jwtUtils.generateToken(Long.valueOf(id));
        response.setHeader("Authorization", jwt);
        //将Authorization在响应首部暴露出来
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        if (flag) {
            Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("adminId", id));
            Teacher teacher = teacherService.getOne(new QueryWrapper<Teacher>().eq("teaId", id));
            Student student = studentService.getOne(new QueryWrapper<Student>().eq("stuId", id));
            if (admin != null) {
                return Result.succ(MapUtil.builder()
                        .put("id", admin.getAdminId())
                        .put("username", "管理员")
                        .put("role", admin.getRole())
                        .map()
                );
            } else if (teacher != null) {
                return Result.succ(MapUtil.builder()
                        //注意这里将long型id字符串 不然前台获取会四舍五入
                        .put("id", teacher.getTeaId())
                        .put("username", teacher.getTeaName())
                        .put("role", teacher.getRole())
                        .map()
                );
            } else {
                return Result.succ(MapUtil.builder()
                        .put("id", student.getStuId())
                        .put("username", student.getStuName())
                        .put("role", student.getRole())
                        .map()
                );
            }
        }
        return Result.succ(null);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        Teacher teacher = teacherService.getOne(new QueryWrapper<Teacher>().eq("teaId", loginDto.getUsername()));

        //教师为空
        if (teacher == null) {
            //判断学生
            Student student = studentService.getOne(new QueryWrapper<Student>().eq("stuId", loginDto.getUsername()));

            //管理员是否为空
            if (student == null) {
                //判断是否为管理员
                Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", loginDto.getUsername()));

                if (admin == null) {
                    //为空则抛出异常 那么全局异常会捕获
                    return Result.fail("用户不存在");
                } else {
                    //管理员存在 判断密码
                    //if (!admin.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
                    if (!SecureUtil.md5(admin.getPassword()).equals(SecureUtil.md5(loginDto.getPassword()))) {
                        //密码不正确
                        return Result.fail("密码不正确");
                    } else {
                        //密码也正确
                        String jwt = jwtUtils.generateToken(admin.getAdminId());
                        response.setHeader("Authorization", jwt);
                        //将Authorization在响应首部暴露出来
                        response.setHeader("Access-control-Expose-Headers", "Authorization");
                        return Result.succ(MapUtil.builder()
                                .put("id", admin.getAdminId())
                                .put("username", "管理员")
                                .put("role", admin.getRole())
                                .map()
                        );
                    }
                }
            } else {
                //学生存在 判断密码
                if (!student.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
                    //密码不正确
                    return Result.fail("密码不正确");
                } else {
                    //密码也正确
                    String jwt = jwtUtils.generateToken(Long.valueOf(student.getStuId()));
                    response.setHeader("Authorization", jwt);
                    response.setHeader("Access-control-Expose-Headers", "Authorization");
                    return Result.succ(MapUtil.builder()
                            .put("id", student.getStuId())
                            .put("username", student.getStuName())
                            .put("role", student.getRole())
                            .map()
                    );
                }
            }
        } else {
            //部门存在 判断密码
            if (!teacher.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
                //密码错误
                return Result.fail("密码不正确");
            } else {
                String jwt = jwtUtils.generateToken(Long.valueOf(teacher.getTeaId()));
                response.setHeader("Authorization", jwt);
                response.setHeader("Access-control-Expose-Headers", "Authorization");
                return Result.succ(MapUtil.builder()
                        //注意这里将long型id字符串 不然前台获取会四舍五入
                        .put("id", teacher.getTeaId())
                        .put("username", teacher.getTeaName())
                        .put("role", teacher.getRole())
                        .map()
                );
            }
        }
    }


    @RequiresAuthentication
    @GetMapping("logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ("退出成功");
    }
}
