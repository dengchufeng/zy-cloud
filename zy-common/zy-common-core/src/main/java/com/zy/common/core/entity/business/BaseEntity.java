package com.zy.common.core.entity.business;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础资料通用 实体类
 *
 * @Author: xc
 * @Date: 2023/9/13 13:39
 */
@Getter
@Setter
public class BaseEntity extends BusinessEntity {

    /**
     * 编码
     */
    @NotBlank(message = "编码不能为空")
    @TableField("fcode")
    private String fcode;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    @TableField("fname")
    private String fname;

}
