package com.mickey.tech.common.core.annotation;

import com.mickey.tech.common.core.enums.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 指定数据源注解
 * @author suzhengxiao
 * @date 2020/1/14 11:25 上午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSourceDB {
    DataSourceEnum model() default DataSourceEnum.PRIMARY;
}
