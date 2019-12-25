package cn.nyse.service.impl;


import cn.nyse.entity.Demand;
import cn.nyse.service.DemandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/24/17:36
 * @Description:
 */
@Service
@Transactional
public class DemandServiceImpl extends ServiceImpl<Demand> implements DemandService {


    @Override
    public PageInfo<Demand> selectPage(Integer pageNum, Integer pageSize) {
        //开启分页拦截  分页插件会自动在最近一个sql执行前，自动添加分页的sql代码 limit x,x
        PageHelper.startPage(pageNum,pageSize);
        List<Demand> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
        return new PageInfo<>(list);
    }
}
