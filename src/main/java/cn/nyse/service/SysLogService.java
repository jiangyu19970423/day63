package cn.nyse.service;

import cn.nyse.entity.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysLogService extends  IService<SysLog>{
    PageInfo<SysLog> selectByCondition(Map<String, Object> params);
}
