package com.zy.common.core.vo.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础资料参数
 *
 * @Author: xc
 * @Date: 2023/9/6 14:45
 */
@Getter
@Setter
public class BaseSearchVo extends PageSearchVo {

    /**
     * 主键id
     */
    private String id;

    /**
     * 编码
     */
    private String fcode;

    /**
     * 名称
     */
    private String fname;

    /**
     * 单据状态（A：创建，C：审核）
     */
    private String billStatus;

}
