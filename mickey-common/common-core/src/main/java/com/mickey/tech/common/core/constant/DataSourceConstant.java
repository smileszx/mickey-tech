package com.mickey.tech.common.core.constant;

/**
 * 多数据源配置的常量
 * @author suzhengxiao
 * @date 2020/1/14 11:29 上午
 */
public final class DataSourceConstant {
    private DataSourceConstant() {
    }

    /**
     * mybatis配置文件名字
     */
    public static final String MYBATIS_CONFIG_FILE_NAME = "mybatis-config.xml";

    /**
     * mapper文件包名
     */
    public static final String MYBATIS_MAPPER_CLASS_PKG = "com.mi.info.groupname.demo.dal.mapper";

    /**
     * 数据库配置的前缀
     */
    public static final String DATA_SOURCE_CONFIG_PREFIX = "data.db.";
}
