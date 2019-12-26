package cn.nyse.service;

import cn.nyse.entity.Examine;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ExamineService extends IService<Examine> {
    PageInfo<Examine> selectPage(Map<String, Object> params);
}
