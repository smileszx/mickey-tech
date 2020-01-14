package com.mickey.tech.common.swagger.config;

import com.mickey.tech.common.swagger.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 * @author suzhengxiao
 * @date 2020/1/6 14:38
 **/
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class SwaggerAutoConfiguration implements WebMvcConfigurer {

    /***
     * 注入自定义配置文件
     */
    @Autowired
    private SwaggerProperties swaggerProperties;

    /**
     * 配置swagger2生成api的参数
     * @return
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启swagger
                .enable(swaggerProperties.getEnabled()).select()
                // 扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(setHeaderToken())
                .pathMapping("/");
    }

    /**
     * 配置swagger2生成api的版本相关信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        tokenParam.name("Authorization").description(swaggerProperties.getHeadTokenDescription())
            .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        params.add(tokenParam.build());
        return params;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (swaggerProperties.getEnabled()) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

}