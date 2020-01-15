package cn.nyse.service;

import cn.nyse.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser>{

    SysUser checkSysUser(SysUser record);

    PageInfo<SysUser> selectByCondition(Map<String,Object> params);

    SysUser selectOneByCondition(long uid);

    List<SysUser> selectByRid(long rid);

    List<SysUser> selectNoRole(long rid, long oid);
}
