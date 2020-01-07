package cn.nyse.service;

import cn.nyse.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface SysAreaService extends IService<SysArea> {
    OutputStream writeExcel(OutputStream outputStream);

    int readExcel(InputStream inputStream);

    PageInfo<SysArea> selectByPage(Map<String, Object> params);

    SysArea selectByAid(long id);

    int updateParentIds(SysArea sysArea);
}
