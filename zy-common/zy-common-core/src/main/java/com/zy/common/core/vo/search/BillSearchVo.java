package com.zy.common.core.vo.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 单据参数
 *
 * @Author: xc
 * @Date: 2023/9/6 14:47
 */
@Getter
@Setter
public class BillSearchVo extends PageSearchVo {

    /**
     * 主键id
     */
    private String id;

    /**
     * 单据状态（A：创建，C：审核）
     */
    private String billStatus;

}
