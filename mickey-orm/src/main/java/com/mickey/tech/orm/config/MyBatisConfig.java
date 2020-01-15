package com.mickey.tech.orm.config;

import com.mickey.tech.common.core.constant.DataSourceConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * MyBatis数据源相关配置
 * @author suzhengxiao
 * @date 2020/1/15 3:50 下午
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = {DataSourceConstant.MYBATIS_MAPPER_CLASS_PKG})
public class MyBatisConfig {

    @Resource
    private DataSource myRoutingDataSource;

    /**
     * 注入SqlSessionFactory
     * @return org.apache.ibatis.session.SqlSessionFactory
     * @author suzhengxiao
     * @date 2020/1/14 3:08 下午
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(DataSourceConstant.MYBATIS_CONFIG_FILE_NAME));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 注入TransactionManager，事务管理
     * @return org.springframework.transaction.PlatformTransactionManager
     * @author suzhengxiao
     * @date 2020/1/14 3:09 下午
     */
    @Bean
    @Primary
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(myRoutingDataSource);
    }
}
