package com.zy.produce.service.nwlbill.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.common.core.constant.OftenConstant;
import com.zy.common.core.exception.ParamCheckException;
import com.zy.common.core.utils.DbDataUtils;
import com.zy.common.core.vo.operation.AuditVo;
import com.zy.common.redis.service.RedisService;
import com.zy.produce.entity.nwlbill.NwlBill;
import com.zy.produce.entity.nwlbill.NwlBillEntry;
import com.zy.produce.mapper.nwlbill.NwlBillMapper;
import com.zy.produce.service.nwlbill.INwlBillEntryService;
import com.zy.produce.service.nwlbill.INwlBillService;
import com.zy.produce.vo.nwlbill.NwlBillSearchVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 多单据 服务实现类
 *
 * @Author xc
 * @Date 2023/09/14 15:33
 */
@Service
public class NwlBillServiceImpl extends ServiceImpl<NwlBillMapper, NwlBill> implements INwlBillService {

    private final RedisService redisService;
    private final INwlBillEntryService nwlBillEntryService;

    public NwlBillServiceImpl(RedisService redisService, INwlBillEntryService nwlBillEntryService) {
        this.redisService = redisService;
        this.nwlBillEntryService = nwlBillEntryService;
    }


    /**
     * 多单据列表
     *
     * @param search 搜索参数
     * @return Page
     */
    @Override
    public Page<NwlBill> nwlBillList(NwlBillSearchVo search) {
        Page<NwlBill> page = DbDataUtils.setPageParam(search.getPageNo(), search.getPageSize());
        List<NwlBill> dataList = this.baseMapper.nwlBillList(page, search);
        return page.setRecords(dataList);
    }

    /**
     * 多单据添加
     *
     * @param nwlBill 多单据参数
     */
    @Override
    @Transactional
    public void add(NwlBill nwlBill) {
        // 单据编码自动生成
        nwlBill.setBillNo(redisService.generateCode("DDJ"));
        // 插入一条记录
        boolean isSave = save(nwlBill);
        if (!isSave) {
            throw new ParamCheckException(OftenConstant.SAVE_ERROR);
        }
        // 多单据明细添加
        addEntry(nwlBill);
    }

    /**
     * 多单据编辑
     *
     * @param nwlBill 多单据参数
     */
    @Override
    @Transactional
    public void edit(NwlBill nwlBill) {
        // 根据id修改
        boolean isUpdate = updateById(nwlBill);
        if (!isUpdate) {
            throw new ParamCheckException(OftenConstant.UPDATE_ERROR);
        }
        // 多单据明细删除
        deleteEntry(nwlBill.getId());
        // 多单据明细添加
        addEntry(nwlBill);
    }

    /**
     * 多单据根据id删除
     *
     * @param id 多单据id
     */
    @Override
    @Transactional
    public void delete(String id) {
        // 验证已审核不能删除
        DbDataUtils.checkAuditedCannotDelete(id, this, true);
        // 根据id删除明细
        deleteEntry(id);
        // 根据id删除表头
        removeById(id);
    }

    /**
     * 多单据批量删除
     *
     * @param ids 多单据id，多个用英文逗号分隔
     */
    @Override
    public void deleteBatch(String ids) {
        Arrays.stream(ids.split(OftenConstant.COMMA_EN)).forEach(this::delete);
    }

    /**
     * 多单据修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @Override
    public void updateAuditStatus(AuditVo auditVo) {
        // 验证审核状态是否已存在
        DbDataUtils.checkAuditStatusExists(auditVo, this, true);
        // 修改审核状态
        DbDataUtils.updateAuditStatus(auditVo, this);
    }

    /**
     * 多单据批量修改审核状态
     *
     * @param auditVo 审核状态参数
     */
    @Override
    public void updateAuditStatusBatch(AuditVo auditVo) {
        Arrays.stream(auditVo.getIds().split(OftenConstant.COMMA_EN))
                .map(id -> new AuditVo(id, auditVo.getOperateStatus(), auditVo.getAuditor()))
                .forEach(this::updateAuditStatus);
    }

    /**
     * 多单据明细添加
     *
     * @param nwlBill 多单据参数
     */
    private void addEntry(NwlBill nwlBill) {
        List<NwlBillEntry> entryList = nwlBill.getEntry();
        if (!CollectionUtils.isEmpty(entryList)) {
            entryList.forEach(entry -> entry.setFid(nwlBill.getId()));
            nwlBillEntryService.saveBatch(entryList);
        }
    }

    /**
     * 多单据明细删除
     *
     * @param fid 多单据id
     */
    private void deleteEntry(String fid) {
        DbDataUtils.deleteEntryByFid(fid, nwlBillEntryService);
    }

}
