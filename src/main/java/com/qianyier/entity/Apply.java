package com.qianyier.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author qianyier
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;


    //主键自增
    @TableId(value = "applyId", type = IdType.AUTO)
    private Integer applyId;

    private String stuId;

    private String teaId;

    private Integer auditState;

    private String applyReason;

    private String rejectReason;


}
