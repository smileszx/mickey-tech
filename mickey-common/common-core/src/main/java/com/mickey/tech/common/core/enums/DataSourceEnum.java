package com.mickey.tech.common.core.enums;

import lombok.Getter;

/**
 * 数据源
 * @author suzhengxiao
 * @date 2020/1/14 11:11 上午
 */
public enum DataSourceEnum {

    /**
     * 主数据源
     */
    PRIMARY("primary"),

    /**
     * 第二数据源
     */
    SECOND("second"),

    /**
     * 第三个数据源
     */
    THIRD("third"),
    ;

    @Getter
    private String configName;

    DataSourceEnum(String configName) {
        this.configName = configName;
    }
}