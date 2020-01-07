import cn.nyse.config.SpringMybatisConfig;
import cn.nyse.entity.SysArea;
import cn.nyse.entity.SysAreaListener;
import cn.nyse.mapper.SysAreaMapper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/03/16:05
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestEasyExcel {

    @Autowired
    SysAreaMapper sysAreaMapper;
    /**
     * 读取java对象信息，写入到excel
     *
     * EasyExcel:封装了excel读写的api
     */
    @Test
    public void testWrite(){
        //获取excel写出对象
        ExcelWriter write = EasyExcel.write("E:\\aaa\\area.xls", SysArea.class).build();
        //获取sheet对象
        WriteSheet sheet = EasyExcel.writerSheet("sysarea数据").build();
        List<SysArea> sysAreas = sysAreaMapper.selectAll();
        write.write(sysAreas,sheet);//将数据写出到excel表的对应sheet位置
        write.finish();
    }


    /**
     * 读取excel信息，变成java对象
     * 1.编写一个实现了监听器接口的类
     * 2.获取excel读取对象
     * 3.获取sheet对象
     * 4.读资源
     *
     */
    @Test
    public void testRead(){
        ExcelReader reader = EasyExcel.read("E:\\aaa\\area.xls",
                SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        reader.read(sheet);
        reader.finish();
    }
}
