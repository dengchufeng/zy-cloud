package com.zy.common.core.entity.business;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 业务通用 实体类
 *
 * @Author: xc
 * @Date: 2023/9/13 13:38
 */
@Getter
@Setter
public class BusinessEntity {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 单据状态（A：创建，C：审核）
     */
    @TableField(value = "bill_status", fill = FieldFill.INSERT)
    private String billStatus;

    /**
     * 创建人
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建日期
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

    /**
     * 修改人
     */
    @TableField("modifier")
    private String modifier;

    /**
     * 修改日期
     */
    @TableField(value = "modify_date", fill = FieldFill.UPDATE)
    private Date modifyDate;

    /**
     * 审核人
     */
    @TableField("auditor")
    private String auditor;

    /**
     * 审核日期
     */
    @TableField("audit_date")
    private Date auditDate;

}
