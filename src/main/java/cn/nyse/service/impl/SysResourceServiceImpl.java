package cn.nyse.service.impl;

import cn.nyse.entity.SysResource;
import cn.nyse.mapper.SysResourceMapper;
import cn.nyse.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResource> implements SysResourceService {


    @Autowired
    SysResourceMapper resourceMapper;


    @Override
    public List<SysResource> selectByRid(long rid){
        return resourceMapper.selectByRid(rid);
    }

    @Override
    public List<SysResource> selectByUid(long uid) {
        List<SysResource> sysResources = resourceMapper.selectByUid(uid);
//        HashSet<SysResource> resourceSet = new HashSet<>();
//        resourceSet.addAll(sysResources);//必须重写equals才能保证去重
        return sysResources;
    }

    @Override
    //@Cacheable(cacheNames = "resourceCache",key = "'cn.nyse.service.impl.SysResourceServiceImpl:selectAll'")
    public List<SysResource> selectAll() {
        return super.selectAll();
    }
}
