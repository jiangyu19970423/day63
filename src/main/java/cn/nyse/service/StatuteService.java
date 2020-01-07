package cn.nyse.service;

import cn.nyse.entity.Statute;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface StatuteService extends IService<Statute>{
    PageInfo<Statute> selectByCondition(Map<String, Object> params);
}
