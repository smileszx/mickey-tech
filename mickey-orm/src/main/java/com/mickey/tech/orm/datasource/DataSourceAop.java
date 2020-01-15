package com.mickey.tech.orm.datasource;

import com.mickey.tech.common.core.annotation.DataSourceDB;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 面向切面方式切换数据源
 * @author suzhengxiao
 * @date 2020/1/14 2:20 下午
 */
@Aspect
@Component
@Order(0)
public class DataSourceAop {

    /**
     * 定义切入点,切入点为DataSourceDB注解
     */
    @Pointcut("@annotation(com.mickey.tech.common.core.annotation.DataSourceDB)")
    public void pointcut() {
    }

    /**
     * 执行符合条件切入点之前切换对应数据源
     * @param jp
     * @return void
     * @author suzhengxiao
     * @date 2020/1/14 2:21 下午
     */
    @Before("pointcut()")
    public void before(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Annotation[] annotations = method.getAnnotations();
        Arrays.stream(annotations).forEach(annotation -> {
            Class annotationType = annotation.annotationType();
            if (annotationType == DataSourceDB.class) {
                DataSourceDB dataSourceDB = (DataSourceDB) annotation;
                DataSourceContextHolder.setDataSource(dataSourceDB.model());
            }
        });
    }

    /**
     * 执行符合条件切入点之后清空数据源
     * @param jp
     * @return void
     * @author suzhengxiao
     * @date 2020/1/14 2:21 下午
     */
    @After("pointcut()")
    public void after(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Annotation[] annotations = method.getAnnotations();
        Arrays.stream(annotations).forEach(annotation -> {
            Class annotationType = annotation.annotationType();
            if (annotationType == DataSourceDB.class) {
                DataSourceContextHolder.clearDataSource();
            }
        });
    }
}
