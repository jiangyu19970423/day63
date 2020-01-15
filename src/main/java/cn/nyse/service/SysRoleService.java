package cn.nyse.service;

import cn.nyse.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends IService<SysRole>{
    PageInfo<SysRole> selectByCondition(Map<String, Object> params);

    SysRole selectOneByCondition(long id);

    int deleteBatch(long rid, long[] uids);

    int insertBatch(List<Long> cids, long rid);

    int update(Map<String, Object> params);
}
