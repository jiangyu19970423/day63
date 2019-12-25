package cn.nyse.service.impl;

import cn.nyse.entity.AppVersion;
import cn.nyse.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/23/18:20
 * @Description:
 */
@Service
@Transactional
public class AppVersionServiceImpl extends ServiceImpl<AppVersion> implements AppVersionService {

    @Override
    public PageInfo<AppVersion> selectPage(Integer pageNum, Integer pageSize) {
       //开启分页拦截  分页插件会自动在最近一个sql执行前，自动添加分页的sql代码 limit x,x
        PageHelper.startPage(pageNum,pageSize);
        List<AppVersion> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
        return new PageInfo<>(list);

    }

    @Override
    public List<AppVersion> selectAll() {
        return mapper.selectAll();
    }
}
