package com.zy.base.service.nwlbase.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.common.core.utils.DbDataUtils;
import com.zy.common.core.vo.operation.AuditVo;
import com.zy.common.core.constant.OftenConstant;
import com.zy.base.entity.nwlbase.NwlBase;
import com.zy.base.mapper.nwlbase.NwlBaseMapper;
import com.zy.base.service.nwlbase.INwlBaseService;
import com.zy.base.vo.nwlbase.NwlBaseSearchVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 单测试 服务实现类
 *
 * @Author xc
 * @Date 2023/09/12 10:20
 */
@Service
public class NwlBaseServiceImpl extends ServiceImpl<NwlBaseMapper, NwlBase> implements INwlBaseService {

    /**
     * 单测试列表
     *
     * @param search 搜索参数
     * @return Page
     */
    @Override
    public Page<NwlBase> nwlBaseList(NwlBaseSearchVo search) {
        Page<NwlBase> page = DbDataUtils.setPageParam(search.getPageNo(), search.getPageSize());
        List<NwlBase> dataList = this.baseMapper.nwlBaseList(page, search);
        return page.setRecords(dataList);
    }

    /**
     * 单测试添加
     *
     * @param nwlBase 单测试参数
     */
    @Override
    public void add(NwlBase nwlBase) {
        // 验证编码是否已存在
        DbDataUtils.checkCodeExists(NwlBase::getFcode, nwlBase.getFcode(), this);
        // 插入一条记录
        save(nwlBase);
    }

    /**
     * 单测试编辑
     *
     * @param nwlBase 单测试参数
     */
    @Override
    public void edit(NwlBase nwlBase) {
        // 验证编码是否已存在
        DbDataUtils.checkUpdateCodeExists(nwlBase.getId(), NwlBase::getFcode, nwlBase.getFcode(), this);
        // 根据id修改
        updateById(nwlBase);
    }

    /**
     * 单测试根据id删除
     *
     * @param id 单测试id
     */
    @Override
    public void delete(String id) {
        // 验证已审核不能删除
        DbDataUtils.checkAuditedCannotDelete(id, this, false);
        // 根据id删除
        removeById(id);
    }

    /**
     * 单测试批量删除
     *
     * @param ids 单测试id，多个用英文逗号分隔
     */
    @Override
    public void deleteBatch(String ids) {
        Arrays.stream(ids.split(OftenConstant.COMMA_EN)).forEach(this::delete);
    }

    /**
     * 单测试修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @Override
    public void updateAuditStatus(AuditVo auditVo) {
        // 验证审核状态是否已存在
        DbDataUtils.checkAuditStatusExists(auditVo, this, false);
        // 修改审核状态
        DbDataUtils.updateAuditStatus(auditVo, this);
    }

    /**
     * 单测试批量修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @Override
    public void updateAuditStatusBatch(AuditVo auditVo) {
        Arrays.stream(auditVo.getIds().split(OftenConstant.COMMA_EN))
                .map(id -> new AuditVo(id, auditVo.getOperateStatus(), auditVo.getAuditor()))
                .forEach(this::updateAuditStatus);
    }

}
