package cn.nyse.service.impl;

import cn.nyse.entity.SysUser;
import cn.nyse.mapper.SysUserMapper;
import cn.nyse.service.SysUserService;
import cn.nyse.utils.EncryptUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/07/15:37
 * @Description:
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser checkSysUser(SysUser record) {
        record.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(record.getPassword()) + record.getUsername()));
        List<SysUser> select = sysUserMapper.select(record);
        if (select.size() > 0) {
            return select.get(0);
        }
        return null;
    }

    @Override
    public PageInfo<SysUser> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
//        SysUserMapper sysUserMapper= (SysUserMapper) mapper;
        List<SysUser> sysUsers = sysUserMapper.selectByCondition(params);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);//生成分页对象

        return pageInfo;
    }

    @Override
    public SysUser selectOneByCondition(long uid) {
        SysUser sysUser = sysUserMapper.selectOneByCondition(uid);
        sysUser.setPassword(null);
        return sysUser;
    }
    @Override
    public List<SysUser> selectByRid(long rid){
        return sysUserMapper.selectByRid(rid);
    }


    @Override
    public List<SysUser> selectNoRole(long rid, long oid){
        return sysUserMapper.selectNoRole(rid,oid);
    }
}
