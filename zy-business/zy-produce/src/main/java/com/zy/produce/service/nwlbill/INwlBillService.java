package com.zy.produce.service.nwlbill;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.core.vo.operation.AuditVo;
import com.zy.produce.entity.nwlbill.NwlBill;
import com.zy.produce.vo.nwlbill.NwlBillSearchVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 多单据 服务类
 *
 * @Author xc
 * @Date 2023/09/14 14:27
 */
public interface INwlBillService extends IService<NwlBill> {

    /**
     * 多单据列表
     *
     * @param search 搜索参数
     * @return Page
     **/
    Page<NwlBill> nwlBillList(NwlBillSearchVo search);

    /**
     * 多单据添加
     *
     * @param nwlBill 多单据参数
     */
    void add(NwlBill nwlBill);

    /**
     * 多单据编辑
     *
     * @param nwlBill 多单据参数
     */
    void edit(NwlBill nwlBill);

    /**
     * 多单据根据id删除
     *
     * @param id 多单据id
     */
    void delete(String id);

    /**
     * 多单据批量删除
     *
     * @param ids 多单据id，多个用英文逗号分隔
     */
    void deleteBatch(String ids);

    /**
     * 多单据修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    void updateAuditStatus(AuditVo auditVo);

    /**
     * 多单据批量修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    void updateAuditStatusBatch(AuditVo auditVo);

}
