package com.zy.common.core.vo.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页参数
 *
 * @Author: xc
 * @Date: 2023/9/6 14:43
 */
@Getter
@Setter
public class PageSearchVo {

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页显示的数量
     */
    private Integer pageSize;

}
