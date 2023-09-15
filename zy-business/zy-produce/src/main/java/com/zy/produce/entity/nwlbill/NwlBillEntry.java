package com.zy.produce.entity.nwlbill;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zy.common.core.entity.business.EntryEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 多单据明细
 *
 * @Author xc
 * @Date 2023/09/14 15:12
 */
@Getter
@Setter
@TableName("nwl_bill_entry")
public class NwlBillEntry extends EntryEntity {

    /**
     * 数量
     */
    @TableField("qty")
    private Double qty;

}