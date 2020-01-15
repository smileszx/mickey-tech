package com.mickey.tech.orm.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

/**
 * 数据源生成管理
 * @author suzhengxiao
 * @date 2020/1/14 2:49 下午
 */
public class DruidDataSourceBuilder {
    private static final String SUFFIX_SEP = ".";
    private static final String NAME = "name";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER_CLASS_NAME = "driver-class-state";
    private static final String INITIAL_SIZE = "initial-size";
    private static final String MAX_ACTIVE = "max-active";
    private static final String MIN_IDLE = "min-idle";
    private static final String MAX_WAIT = "max-wait";
    private static final String POOL_PREPARED_STATEMENTS = "pool-prepared-statements";
    private static final String MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE = "max-pool-prepared-statement-per-connection-size";
    private static final String VALIDATION_QUERY = "validation-query";
    private static final String VALIDATION_QUERY_TIMEOUT = "validation-query-timeout";
    private static final String TEST_WHILE_IDLE = "test-while-idle";
    private static final String TEST_ON_BORROW = "test-on-borrow";
    private static final String TEST_ON_RETURN = "test-on-return";
    private static final String TIME_BETWEEN_EVICTION_RUNS_MILLIS = "time-between-eviction-runs-millis";
    private static final String MIN_EVICTABLE_IDLE_TIME_MILLIS = "min-evictable-idle-time-millis";
    private static final String MAX_EVICTABLE_IDLE_TIME_MILLIS = "max-evictable-idle-time-millis";
    private static final String FILTERS = "filters";
    private static final int DEFAULT_TIMEOUT = 0;
    private static final int DEFAULT_SIZE = 0;

    public static DruidDataSourceBuilder create() {
        return new DruidDataSourceBuilder();
    }

    public DruidDataSource build(Environment env, String prefix) throws SQLException {
        if (!prefix.endsWith(SUFFIX_SEP)) {
            prefix += ".";
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(env.getProperty(prefix + NAME));
        druidDataSource.setUrl(env.getProperty(prefix + URL));
        druidDataSource.setUsername(env.getProperty(prefix + USERNAME));
        druidDataSource.setPassword(env.getProperty(prefix + PASSWORD));
        druidDataSource.setDriverClassName(env.getProperty(prefix + DRIVER_CLASS_NAME));
        druidDataSource.setInitialSize(env.getProperty(prefix + INITIAL_SIZE, Integer.class, DruidDataSource.DEFAULT_INITIAL_SIZE));
        druidDataSource.setMaxActive(env.getProperty(prefix + MAX_ACTIVE, Integer.class, DruidDataSource.DEFAULT_MAX_ACTIVE_SIZE));
        druidDataSource.setMinIdle(env.getProperty(prefix + MIN_IDLE, Integer.class, DruidDataSource.DEFAULT_MIN_IDLE));
        druidDataSource.setMaxWait(env.getProperty(prefix + MAX_WAIT, Long.class, (long) DruidDataSource.DEFAULT_MAX_WAIT));
        druidDataSource.setPoolPreparedStatements(env.getProperty(prefix + POOL_PREPARED_STATEMENTS, Boolean.class, false));
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(env.getProperty(prefix + MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE, Integer.class, DEFAULT_SIZE));
        druidDataSource.setValidationQuery(env.getProperty(prefix + VALIDATION_QUERY));
        druidDataSource.setValidationQueryTimeout(env.getProperty(prefix + VALIDATION_QUERY_TIMEOUT, Integer.class, DEFAULT_TIMEOUT));
        druidDataSource.setTestWhileIdle(env.getProperty(prefix + TEST_WHILE_IDLE, Boolean.class, true));
        druidDataSource.setTestOnBorrow(env.getProperty(prefix + TEST_ON_BORROW, Boolean.class, false));
        druidDataSource.setTestOnReturn(env.getProperty(prefix + TEST_ON_RETURN, Boolean.class, false));
        druidDataSource.setTimeBetweenEvictionRunsMillis(env.getProperty(prefix + TIME_BETWEEN_EVICTION_RUNS_MILLIS, Long.class, DruidDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS));
        druidDataSource.setMinEvictableIdleTimeMillis(env.getProperty(prefix + MIN_EVICTABLE_IDLE_TIME_MILLIS, Long.class, DruidDataSource.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS));
        druidDataSource.setMaxEvictableIdleTimeMillis(env.getProperty(prefix + MAX_EVICTABLE_IDLE_TIME_MILLIS, Long.class, DruidDataSource.DEFAULT_MAX_EVICTABLE_IDLE_TIME_MILLIS));
        druidDataSource.setFilters(env.getProperty(prefix + FILTERS));
        return druidDataSource;
    }

}
