package com.zy.base.service.nwlbase;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.core.vo.operation.AuditVo;
import com.zy.base.entity.nwlbase.NwlBase;
import com.zy.base.vo.nwlbase.NwlBaseSearchVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 单测试 服务类
 *
 * @Author xc
 * @Date 2023/09/12 10:18
 */
public interface INwlBaseService extends IService<NwlBase> {

    /**
     * 单测试列表
     *
     * @param search 搜索参数
     * @return Page
     **/
    Page<NwlBase> nwlBaseList(NwlBaseSearchVo search);

    /**
     * 单测试添加
     *
     * @param nwlBase 单测试参数
     */
    void add(NwlBase nwlBase);

    /**
     * 单测试编辑
     *
     * @param nwlBase 单测试参数
     */
    void edit(NwlBase nwlBase);

    /**
     * 单测试根据id删除
     *
     * @param id 单测试id
     */
    void delete(String id);

    /**
     * 单测试批量删除
     *
     * @param ids 单测试id，多个用英文逗号分隔
     */
    void deleteBatch(String ids);

    /**
     * 单测试修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    void updateAuditStatus(AuditVo auditVo);

    /**
     * 单测试批量修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    void updateAuditStatusBatch(AuditVo auditVo);

}
