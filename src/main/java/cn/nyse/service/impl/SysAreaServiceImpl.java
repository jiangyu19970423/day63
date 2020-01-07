package cn.nyse.service.impl;

import cn.nyse.entity.SysArea;
import cn.nyse.entity.SysAreaListener;
import cn.nyse.mapper.SysAreaMapper;
import cn.nyse.service.SysAreaService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/03/16:18
 * @Description:
 */
@Service
@Transactional
public class SysAreaServiceImpl extends ServiceImpl<SysArea> implements SysAreaService {
    @Autowired
    SysAreaMapper sysAreaMapper;

    /**
     * 根据传入的输出流对象，进行excel写入操作，并返回该输出流对象
     *
     * @param outputStream
     * @return
     */
    @Override
    public OutputStream writeExcel(OutputStream outputStream) {
        //获取excel写出对象
        ExcelWriter write = EasyExcel.write(outputStream, SysArea.class).build();
        //获取sheet对象
        WriteSheet sheet = EasyExcel.writerSheet("sysarea数据").build();
        List<SysArea> sysAreas = sysAreaMapper.selectAll();
        write.write(sysAreas, sheet);//将数据写出到excel表的对应sheet位置
        write.finish();
        return outputStream;
    }

    /**
     * 将传入的excel文件的组成的inputStream流读取，转换成java对象，并且进行批量插入到数据库
     *
     * @param inputStream
     * @return
     */
    @Override
    public int readExcel(InputStream inputStream) {
        int result = 0;
        ExcelReader reader = EasyExcel.read(inputStream,
                SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        reader.read(sheet);
        reader.finish();
        result += 1;
        return result;
    }

    /**
     * 根据父区域id或者区域名或者不带条件查找所有区域
     *
     * @return
     */
    @Override
    public PageInfo<SysArea> selectByPage(Map<String, Object> params) {
        //{"aid":''}        {}
        //默认值设置
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageInfo<SysArea> pageInfo = null;
        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));
        //根据父区域id的查询
        if (params.containsKey("aid") && !StringUtils.isEmpty(params.get("aid"))) {
            int id = (Integer) params.get("aid");
            List<SysArea> list = sysAreaMapper.selectByPid((long) id);
            pageInfo = new PageInfo<>(list);
        }
        return pageInfo;
    }

    @Override
    public SysArea selectByAid(long id) {
        SysArea sysArea = sysAreaMapper.selectByAid(id);
        sysArea.setOldParentIds(sysArea.getParentIds());//给旧Id绑定数据
        return sysArea;
    }

    /**
     * 1.更新区域的信息
     * 2.根据当前区域是否有更新parentIds来判断是否要更新所有的子区域的parentIds
     *
     * @param sysArea
     * @return
     */
    @Override
    public int updateParentIds(SysArea sysArea) {
        int i = 0;
        i += sysAreaMapper.updateByPrimaryKey(sysArea);
        if (!sysArea.getOldParentIds().equals(sysArea.getParentIds())) {
            i += sysAreaMapper.updateParentIds(sysArea);//更新所有的子区域的parentIds
        }
        return i;
    }
}
