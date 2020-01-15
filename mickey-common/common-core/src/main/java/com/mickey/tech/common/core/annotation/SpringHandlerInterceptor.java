package com.mickey.tech.common.core.annotation;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义Interceptor注解
 * @author suzhengxiao
 * @date 2020/1/15 11:22
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SpringHandlerInterceptor {

    String[] includePatterns() default {};

    String[] excludePatterns() default {};

    int order() default Ordered.LOWEST_PRECEDENCE;
}
