package cn.nyse.service;

import cn.nyse.entity.AppVersion;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppVersionService extends IService<AppVersion> {
    PageInfo<AppVersion> selectPage(Integer pageNum,Integer pageSize);

}
