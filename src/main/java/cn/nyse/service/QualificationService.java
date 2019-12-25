package cn.nyse.service;

import cn.nyse.entity.Qualification;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface QualificationService extends IService<Qualification> {
    PageInfo<Qualification> selectPage(Map<String, Object> params);
    long selectOfficeId(long oid);
}
