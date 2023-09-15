package com.zy.common.core.entity.business;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

/**
 * 单据通用 实体类
 *
 * @Author: xc
 * @Date: 2023/9/13 13:41
 */
@Getter
@Setter
public class BillEntity extends BusinessEntity {

    /**
     * 单据编码
     */
    @TableField("bill_no")
    private String billNo;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}
