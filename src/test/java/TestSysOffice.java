import cn.nyse.config.SpringMybatisConfig;
import cn.nyse.entity.SysOffice;
import cn.nyse.mapper.SysOfficeMapper;
import cn.nyse.service.SysOfficeService;
import cn.nyse.service.impl.SysOfficeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/02/17:58
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestSysOffice {
    @Autowired
    SysOfficeMapper mapper;

    @Autowired
    SysOfficeService service;

    @Test
    public void test(){
        List<SysOffice> sysOffices = mapper.selectAll();
        List<SysOffice> sysOffices1 = service.selectAll();

    }


    @Test
    public void test2(){
        SysOffice sysOffice = service.selectByPrimaryKey(2);
        sysOffice.setName("新用户注册机构test");
        service.updateByPrimaryKeySelective(sysOffice);
    }
}
