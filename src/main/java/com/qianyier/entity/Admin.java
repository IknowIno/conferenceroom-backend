package com.qianyier.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qianyier
 * @Description lombook Accessors链式访问
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
        private Integer adminId;

    /**
     * 管理员用户名
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 角色是管理员
     */
    private String role;


}
