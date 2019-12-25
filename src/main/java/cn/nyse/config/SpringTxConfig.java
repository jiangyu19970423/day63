package cn.nyse.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/23/18:15
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "cn.nyse.service")
@EnableTransactionManagement
public class SpringTxConfig {
    @Bean
    public DataSourceTransactionManager getTransactionManager(DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
