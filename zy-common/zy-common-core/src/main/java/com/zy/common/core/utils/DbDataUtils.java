package com.zy.common.core.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.core.constant.DbConstant;
import com.zy.common.core.constant.OftenConstant;
import com.zy.common.core.entity.BillBase;
import com.zy.common.core.exception.ParamCheckException;
import com.zy.common.core.vo.operation.AuditVo;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 操作数据库工具类
 *
 * @Author: xc
 * @Date: 2023/9/5 13:55
 */
public class DbDataUtils {

    private DbDataUtils() {
    }


    /**
     * 设置当前页和每页数量
     *
     * @param pageNo   当前页码
     * @param pageSize 每页显示的数量
     * @return 包含设置的页码和每页数量的 Page 对象
     **/
    public static <T> Page<T> setPageParam(Integer pageNo, Integer pageSize) {
        pageNo = (pageNo != null && pageNo != 0) ? pageNo : 1;
        pageSize = (pageSize != null && pageSize != 0) ? pageSize : 10;
        return new Page<>(pageNo, pageSize);
    }

    /**
     * 根据字段查询单条记录，只查询id字段
     *
     * @param function   字段名函数式接口
     * @param fieldValue 字段名的值
     * @param service    通用接口
     * @param <T>        实体类泛型
     * @param <R>        字段名泛型
     * @return 符合查询条件的实体对象，如果找不到匹配的记录则返回null
     */
    public static <T, R> T getByField(SFunction<T, R> function, String fieldValue, IService<T> service) {
        return service.getOne(getByFieldWrapper(function, fieldValue));
    }

    /**
     * 验证编码是否已存在
     *
     * @param function   字段名函数式接口
     * @param fieldValue 字段名的值
     * @param service    通用接口
     * @param <T>        实体类泛型
     * @param <R>        字段名泛型
     */
    public static <T, R> void checkCodeExists(SFunction<T, R> function, String fieldValue, IService<T> service) {
        checkFieldExists(function, fieldValue, service, OftenConstant.CODE_IS_EXIST);
    }

    /**
     * 验证某个字段的值是否已存在
     *
     * @param function     字段名函数式接口
     * @param fieldValue   字段名的值
     * @param service      通用接口
     * @param errorMessage 异常消息
     * @param <T>          实体类泛型
     * @param <R>          字段名泛型
     */
    public static <T, R> void checkFieldExists(SFunction<T, R> function, String fieldValue, IService<T> service, String errorMessage) {
        if (Objects.nonNull(getByField(function, fieldValue, service))) {
            throwFieldException(fieldValue, errorMessage);
        }
    }

    /**
     * 验证修改时编码是否已存在
     *
     * @param id         主键id
     * @param function   字段名函数式接口
     * @param fieldValue 字段名的值
     * @param service    通用接口
     * @param <T>        实体类泛型
     * @param <R>        字段名泛型
     */
    public static <T, R> void checkUpdateCodeExists(String id, SFunction<T, R> function, String fieldValue, IService<T> service) {
        checkUpdateFieldExists(id, function, fieldValue, service, OftenConstant.CODE_IS_EXIST);
    }

    /**
     * 验证修改时某个字段的值是否已存在
     *
     * @param id           主键id
     * @param function     字段名函数式接口
     * @param fieldValue   字段名的值
     * @param service      通用接口
     * @param errorMessage 异常消息
     * @param <T>          实体类泛型
     * @param <R>          字段名泛型
     */
    public static <T, R> void checkUpdateFieldExists(String id, SFunction<T, R> function, String fieldValue, IService<T> service, String errorMessage) {
        // 过滤掉自身id
        QueryWrapper<T> wrapper = getByFieldWrapper(function, fieldValue);
        wrapper.ne(OftenConstant.ID, id);
        // 不为空时抛异常提示
        if (Objects.nonNull(service.getOne(wrapper))) {
            throwFieldException(fieldValue, errorMessage);
        }
    }

    /**
     * 验证已审核不能删除
     *
     * @param id      主键idi
     * @param service 通用接口
     * @param isBill  是否单据：区分单据和基础资料
     * @param <T>     实体类泛型
     */
    public static <T> void checkAuditedCannotDelete(String id, IService<T> service, boolean isBill) {
        // 获取区分单据和基础资料的对象
        BillBase billBase = getBillBase(id, service, isBill);
        // 已审核不能删除
        if (OftenConstant.C.equals(billBase.getBillStatus())) {
            throwFieldException((isBill ? billBase.getBillNo() : billBase.getFcode()), OftenConstant.AUDITED_NOT_DELETE);
        }
    }

    /**
     * 验证审核状态是否已存在
     *
     * @param auditVo 审核状态参数
     * @param service 通用接口
     * @param isBill  是否单据：区分单据和基础资料
     * @param <T>     实体类泛型
     */
    public static <T> void checkAuditStatusExists(AuditVo auditVo, IService<T> service, boolean isBill) {
        // 操作状态（A：审核，B：反审核）
        String operateStatus = auditVo.getOperateStatus();
        if (!OftenConstant.A.equals(operateStatus) && !OftenConstant.B.equals(operateStatus)) {
            throw new ParamCheckException(OftenConstant.OPERATE_STATUS_ERROR);
        }
        BillBase billBase = getBillBase(auditVo.getIds(), service, isBill); // 获取区分单据和基础资料的对象
        boolean isAudit = OftenConstant.C.equals(billBase.getBillStatus()); // 单据状态是否等于审核
        // 审核时验证是否已审核，反审时验证是否已反审
        if ((OftenConstant.A.equals(operateStatus) && isAudit) || (OftenConstant.B.equals(operateStatus) && !isAudit)) {
            String fieldValue = isBill ? billBase.getBillNo() : billBase.getFcode();
            String errorMessage = isAudit ? OftenConstant.IS_AUDITED : OftenConstant.IS_UN_AUDITED;
            throwFieldException(fieldValue, errorMessage);
        }
    }

    /**
     * 修改审核状态
     *
     * @param auditVo 审核状态参数
     * @param <T>     实体类泛型
     * @return 更新操作是否成功的布尔值。如果更新成功，则返回 true；否则返回 false。
     */
    public static <T> boolean updateAuditStatus(AuditVo auditVo, IService<T> service) {
        // 存放审核状态编辑参数
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(OftenConstant.ID, auditVo.getIds());
        // 操作状态（A：审核，B：反审核）
        String operateStatus = auditVo.getOperateStatus();
        Date auditDate = OftenConstant.A.equals(operateStatus) ? new Date() : null;
        String auditor = OftenConstant.A.equals(operateStatus) ? auditVo.getAuditor() : OftenConstant.EMPTY;
        String billStatus = OftenConstant.A.equals(operateStatus) ? OftenConstant.C : OftenConstant.A;
        // 审核日期、审核人和单据状态赋值
        updateWrapper.set(OftenConstant.DB_AUDIT_DATE, auditDate);
        updateWrapper.set(OftenConstant.AUDITOR, auditor);
        updateWrapper.set(OftenConstant.DB_BILL_STATUS, billStatus);
        // 根据UpdateWrapper条件，更新记录
        return service.update(updateWrapper);
    }

    /**
     * 根据父id删除明细
     *
     * @param fid     父id
     * @param service 通用接口
     * @param <T>     实体类泛型
     */
    public static <T> void deleteEntryByFid(String fid, IService<T> service) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(OftenConstant.FID, fid);
        service.remove(queryWrapper);
    }

    /**
     * 获取区分单据和基础资料的对象
     *
     * @param id      主键idi
     * @param service 通用接口
     * @param isBill  是否单据：区分单据和基础资料
     * @param <T>     实体类泛型
     * @return 区分单据和基础资料的对象，如果获取到数据则返回对象；否则抛出异常
     */
    private static <T> BillBase getBillBase(String id, IService<T> service, boolean isBill) {
        // 根据id查询数据
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.select(OftenConstant.ID, OftenConstant.DB_BILL_STATUS, (isBill ? OftenConstant.DB_BILL_NO : OftenConstant.F_CODE))
                .eq(OftenConstant.ID, id);
        T data = service.getOne(wrapper);
        if (Objects.isNull(data)) {
            throwFieldException(id, OftenConstant.DATA_NOT_EXIST);
        }
        // 此对象适配单据编码和基础资料编码
        BillBase billBase = new BillBase();
        BeanUtils.copyProperties(data, billBase);
        return billBase;
    }

    /**
     * 根据字段查询单条记录的Wrapper，只查询id字段
     *
     * @param function   字段名函数式接口
     * @param fieldValue 字段名的值
     * @param <T>        实体类泛型
     * @param <R>        字段名泛型
     * @return 查询单条记录的 QueryWrapper 对象
     */
    private static <T, R> QueryWrapper<T> getByFieldWrapper(SFunction<T, R> function, String fieldValue) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.select(OftenConstant.ID)
                .lambda().eq(function, fieldValue).last(DbConstant.LIMIT_1_SQL);
        return wrapper;
    }

    /**
     * 字段验证异常
     *
     * @param fieldValue   字段名的值
     * @param errorMessage 异常消息
     */
    private static void throwFieldException(String fieldValue, String errorMessage) {
        throw new ParamCheckException(OftenConstant.LEFT_BRACKET_EN + fieldValue + OftenConstant.RIGHT_BRACKET_EN + errorMessage);
    }

}
