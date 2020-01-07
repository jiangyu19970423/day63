package cn.nyse.service;

import cn.nyse.entity.WorkOrder;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface WorkOrderService extends IService<WorkOrder> {

    PageInfo<WorkOrder> selectPage(Map<String, Object> params);
    //根据workOrder的id查询一个详单信息
    Map<String,Object> selectByOid(long oid);
}
