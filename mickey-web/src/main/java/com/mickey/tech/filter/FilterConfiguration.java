package com.mickey.tech.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suzhengxiao
 * @date 2020/3/27 1:53 下午
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean registerLogCostFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogCostFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }
}
