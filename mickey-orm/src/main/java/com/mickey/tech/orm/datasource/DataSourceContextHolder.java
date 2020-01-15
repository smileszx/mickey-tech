package com.mickey.tech.orm.datasource;

import com.mickey.tech.common.core.enums.DataSourceEnum;

/**
 * 线程上下文中的数据源管理
 * @author suzhengxiao
 * @date 2020/1/14 2:30 下午
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSourceEnum 数据源类型
     * @return void
     * @author suzhengxiao
     * @date 2020/1/14 2:39 下午
     */
    public static void setDataSource(DataSourceEnum dataSourceEnum) {
        CONTEXT_HOLDER.set(dataSourceEnum);
    }

    /**
     * 获取数据源
     * @return com.mi.info.groupname.demo.dal.datasource.DataSourceEnum
     * @author suzhengxiao
     * @date 2020/1/14 2:42 下午
     */
    public static DataSourceEnum getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源
     * @author suzhengxiao
     * @date 2020/1/14 2:37 下午
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }


}
