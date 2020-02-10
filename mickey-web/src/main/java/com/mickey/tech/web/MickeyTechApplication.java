package com.mickey.tech.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author suzhengxiao
 * @date 2020/1/14 20:19
 **/
@SpringBootApplication
public class MickeyTechApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MickeyTechApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MickeyTechApplication.class);
    }

}
