package cn.nyse.service;

import cn.nyse.entity.SysArea;
import cn.nyse.entity.SysOffice;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysOfficeService extends IService<SysOffice> {


    PageInfo<SysOffice> selectByCondition(Map<String, Object> params);

    SysOffice selectByOid(long oid);

    int update(SysOffice sysOffice);

    List<SysOffice> selectByRid(long rid);

}
