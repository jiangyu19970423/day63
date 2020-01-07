package cn.nyse.service.impl;

import cn.nyse.entity.Detail;
import cn.nyse.entity.SysUser;
import cn.nyse.entity.Transfer;
import cn.nyse.entity.WorkOrder;
import cn.nyse.mapper.DetailMapper;
import cn.nyse.mapper.SysUserMapper;
import cn.nyse.mapper.TransferMapper;
import cn.nyse.mapper.WorkOrderMapper;
import cn.nyse.service.WorkOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/27/15:52
 * @Description:
 */
@Service
@Transactional
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrder> implements WorkOrderService {
    @Autowired
    WorkOrderMapper workOrderMapper;
    @Autowired
    SysUserMapper userMapper;

    @Autowired
    DetailMapper detailMapper;

    @Autowired
    TransferMapper transferMapper;
    @Override
    public PageInfo<WorkOrder> selectPage(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));

        List<WorkOrder> WorkOrders = workOrderMapper.selectByCondition(params);

        PageInfo<WorkOrder> pageInfo = new PageInfo<>(WorkOrders);//生成分页对象

        return pageInfo;
    }
    /**
     * 1.根据oid查询 workOrder信息
     * 2.根据oid查询创建、运输、处理用户
     * 3.根据oid查询详单
     * 4.根据oid查询转运记录
     * @param oid
     * @return
     */
    @Override
    public Map<String, Object> selectByOid(long oid) {
        WorkOrder workOrder = mapper.selectByPrimaryKey(oid);
        SysUser createUser = userMapper.selectById(workOrder.getCreateUserId());


        SysUser transportUser = null;
        if(!StringUtils.isEmpty(workOrder.getTransportUserId())){
            transportUser=  userMapper.selectById(workOrder.getTransportUserId());
        }

        SysUser recipientUser = null;
        if(!StringUtils.isEmpty(workOrder.getRecipientUserId())){
            recipientUser = userMapper.selectById(workOrder.getRecipientUserId());
        }
        List<Detail> details = detailMapper.selectByOid(oid);
        List<Transfer> transfers = transferMapper.selectByOid(oid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("work",workOrder);
        map.put("createUser",createUser);
        map.put("transportUser",transportUser);
        map.put("recipientUser",recipientUser);
        map.put("details",details);
        map.put("transfers",transfers);

        return map;
    }
}
