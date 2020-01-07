package cn.nyse.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/23/17:57
 * @Description:
 */
/**
 * spring与mybatis整合配置：
 * 1.数据源配置
 * 2.SqlSessionFactoryBean配置
 * 3.开启扫描mapper接口
 *
 * spring整合日志：
 * 1.导入log4j2\slf4j依赖
 * 2.在resources下放入log4j2.xml
 * 3.在mybtis的configuration设置使用log4j2的日志
 */
@Configuration
@MapperScan(basePackages = "cn.nyse.mapper")
@Import({SpringTxConfig.class,SpringCacheConfig.class})
@PropertySource(value = "classpath:system.properties",encoding = "utf-8")
public class SpringMybatisConfig {
    //1.数据源配置
    @Bean
    public DruidDataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        InputStream is = SpringMybatisConfig.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        druidDataSource.configFromPropety(properties);//自动匹配参数
        return druidDataSource;
    }

    /*
     * 替代原mybatis的总配置文件，用于读取数据源获取一个连接回话对象的工厂bean
     * */
    @Bean
    public SqlSessionFactoryBean getFactoryBean(DruidDataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);//设置数据源
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//设置驼峰命名
        configuration.setLogImpl(Log4j2Impl.class);//设置log4j2日志输出
        factoryBean.setConfiguration(configuration);
        PageInterceptor pageInterceptor = new PageInterceptor();//分页拦截对象
        pageInterceptor.setProperties(new Properties());
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        return factoryBean;

    }
}
