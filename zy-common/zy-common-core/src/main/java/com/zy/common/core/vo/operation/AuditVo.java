package com.zy.common.core.vo.operation;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 修改审核状态参数
 *
 * @Author: xc
 * @Date: 2023/9/8 14:57
 */
@Getter
@Setter
@AllArgsConstructor // 当此对象添加新的属性时，要适配已使用此对象的方法，添加一个3参构造器即可
public class AuditVo {

    /**
     * 主键id，多个用英文逗号分隔
     */
    @NotBlank(message = "主键id不能为空")
    private String ids;

    /**
     * 操作状态（A：审核，B：反审核）
     */
    @NotBlank(message = "操作状态不能为空")
    private String operateStatus;

    /**
     * 审核人
     */
    private String auditor;

}
