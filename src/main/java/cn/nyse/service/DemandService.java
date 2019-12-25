package cn.nyse.service;

import cn.nyse.entity.Demand;
import com.github.pagehelper.PageInfo;

public interface DemandService extends IService<Demand>{
    PageInfo<Demand> selectPage(Integer pageNum, Integer pageSize);
}
