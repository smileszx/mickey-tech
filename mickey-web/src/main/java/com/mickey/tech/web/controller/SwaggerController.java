package com.mickey.tech.web.controller;

import com.mickey.tech.common.core.util.CommonResult;
import com.mickey.tech.web.vo.SwaggerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Swagger文档生成测试
 * @author suzhengxiao
 * @date 2020/1/10 16:04
 **/
@Slf4j
@RestController
@RequestMapping("/swagger")
@Api(tags="Swagger测试API")
public class SwaggerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SwaggerController.class);

    @ApiOperation(value="问候世界")
    @GetMapping("/hi/{msg}")
    @ApiImplicitParam(name="msg",value="问候信息",required=true)
    public CommonResult sayHello (@Valid @PathVariable("msg") String msg) {
        LOGGER.info("hello, {}", msg);
        return CommonResult.success(msg);
    }

    @ApiOperation(value = "参数测试")
    @PostMapping("/param")
    public CommonResult testParam (@Valid @RequestBody SwaggerVO swaggerVO) {
        LOGGER.info("参数: {}", swaggerVO);
        return CommonResult.success(swaggerVO);
    }

}
