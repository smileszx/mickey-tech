package com.mickey.tech.orm;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义Mapper
 * @author suzhengxiao
 * @date 2020/2/28 22:02
 **/
public interface MyMapper<T> extends
        BaseMapper<T>,
        ConditionMapper<T>,
        MySqlMapper<T>,
        Mapper<T>
{}
