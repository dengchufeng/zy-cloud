package com.zy.produce.entity.nwlbill;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zy.common.core.entity.business.BillEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 多单据
 *
 * @Author xc
 * @Date 2023/09/14 15:31
 */
@Getter
@Setter
@TableName("nwl_bill")
public class NwlBill extends BillEntity {

    /**
     * 客户PO
     */
    @TableField("customer_po")
    private String customerPo;


    /**
     * 多单据明细
     */
    @TableField(exist = false)
    private List<NwlBillEntry> entry;

}