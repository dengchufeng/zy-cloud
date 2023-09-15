package com.zy.common.core.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 单据和基础资料通用字段
 *
 * @Author: xc
 * @Date: 2023/9/8 9:16
 */
@Getter
@Setter
public class BillBase {

    /**
     * 单据编码
     */
    private String billNo;

    /**
     * 单据状态（A：创建，C：审核）
     */
    private String billStatus;

    /**
     * 基础资料编码
     */
    private String fcode;

}
