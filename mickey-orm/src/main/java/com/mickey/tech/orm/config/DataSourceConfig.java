package com.mickey.tech.orm.config;

import com.mickey.tech.common.core.constant.DataSourceConstant;
import com.mickey.tech.common.core.enums.DataSourceEnum;
import com.mickey.tech.orm.datasource.DruidDataSourceBuilder;
import com.mickey.tech.orm.datasource.MyRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载数据源
 * @author suzhengxiao
 * @date 2020/1/14 4:13 下午
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    /**
     * @return javax.sql.DataSource
     * @author suzhengxiao
     * @date 2020/1/14 4:12 下午
     */
    @Bean
    public DataSource myRoutingDataSource() throws SQLException {
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
            DataSource dataSource = genDatasource(environment, DataSourceConstant.DATA_SOURCE_CONFIG_PREFIX + dataSourceEnum.getConfigName());
            targetDataSources.put(dataSourceEnum, dataSource);
        }
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(targetDataSources.get(DataSourceEnum.PRIMARY));
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }

    /**
     * 生成数据源
     * @param environment
     * @param datasourcePrefix
     * @return javax.sql.DataSource
     * @author suzhengxiao
     * @date 2020/1/14 4:15 下午
     */
    private DataSource genDatasource(Environment environment, String datasourcePrefix) throws SQLException {
        return DruidDataSourceBuilder.create().build(environment, datasourcePrefix);
    }

}
