package com.zy.common.core.constant;

/**
 * 常用常数类
 *
 * @Author: xc
 * @Date: 2023/9/4 16:42
 */
public class OftenConstant {

    private OftenConstant() {
    }

    // 字段名称：带下划线用于数据库查询
    public static final String ID = "id";                           // 主键id
    public static final String FID = "fid";                         // 父id
    public static final String F_CODE = "fcode";                    // 基础资料编码
    public static final String BILL_STATUS = "billStatus";          // 单据状态
    public static final String CREATE_DATE = "createDate";          // 创建日期
    public static final String MODIFY_DATE = "modifyDate";          // 修改日期
    public static final String AUDITOR = "auditor";                 // 审核人
    public static final String DB_BILL_NO = "bill_no";              // _单据编码
    public static final String DB_BILL_STATUS = "bill_status";      // _单据状态
    public static final String DB_AUDIT_DATE = "audit_date";        // _审核日期

    // 标点符号
    public static final String LEFT_BRACKET_EN = "[";
    public static final String RIGHT_BRACKET_EN = "]";
    public static final String COMMA_CN = "，";
    public static final String COMMA_EN = ",";
    public static final String EMPTY = "";

    // 大写字母
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";

    // 操作提示
    public static final String OPERATION_SUCCESS = "操作成功";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String BATCH_DELETE_SUCCESS = "批量删除成功";
    public static final String CODE_IS_EXIST = "编码已存在";
    public static final String DATA_NOT_EXIST = "数据不存在";
    public static final String IS_AUDITED = "已审核";
    public static final String IS_UN_AUDITED = "已反核";
    public static final String AUDITED_NOT_DELETE = "已审核，不能删除";
    public static final String SAVE_ERROR = "插入失败，请稍后重试";
    public static final String UPDATE_ERROR = "修改失败，请稍后重试";
    public static final String OPERATE_STATUS_ERROR = "操作状态传参错误，请传入有效值（A：审核，B：反审核）";

}
