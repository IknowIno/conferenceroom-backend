package com.qianyier.shiro;

import cn.hutool.core.bean.BeanUtil;

import com.qianyier.entity.Admin;

import com.qianyier.entity.Student;
import com.qianyier.entity.Teacher;
import com.qianyier.service.AdminService;

import com.qianyier.service.StudentService;
import com.qianyier.service.TeacherService;
import com.qianyier.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    TeacherService teacherService;

    @Autowired
    AdminService adminService;

    @Autowired
    StudentService studentService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //强制转换 会报错 通过工具类拷贝
        Object principal = principals.getPrimaryPrincipal();
        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(principal,accountProfile);

        //在认证的时候存的是只有id 和 role  取出来role放进去即可
        //System.out.println("授权:"+accountProfile);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(accountProfile.getRole());
        return simpleAuthorizationInfo;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) token;

        //有可能是老师id  也可能是admin id 也有可能是学生
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        //判断账号是否存在
        Teacher teacher = teacherService.getById(userId);
        if(teacher==null){
            Student student = studentService.getById(userId);
            if(student ==null){
               Admin admin = adminService.getById(Long.valueOf(userId));
                if(admin == null) {
                    throw new UnknownAccountException("用户不存在");
                }else{
                    //管理员
                    AccountProfile profile = new AccountProfile();
                    profile.setId(String.valueOf(admin.getAdminId()));
                    profile.setRole(admin.getRole());

                    return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
                }
            }else{
                //学生
                System.out.println(student);
                AccountProfile profile = new AccountProfile();
                profile.setId(String.valueOf(student.getStuId()));
                profile.setRole(student.getRole());

                return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
            }
        }else{
            //教师
            AccountProfile profile = new AccountProfile();
            profile.setId(teacher.getTeaId());
            profile.setRole(teacher.getRole());

            return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
        }


    }
}
