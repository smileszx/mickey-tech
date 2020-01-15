package com.mickey.tech.web.controller;

import com.mickey.tech.common.core.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Log测试
 * @author suzhengxiao
 * @date 2020/1/15 11:28
 **/
@Slf4j
@RestController
@RequestMapping("/log")
@Api(tags="Log测试API")
public class LogController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @ApiOperation(value="测试异常日志")
    @GetMapping("/error/{value}")
    @ApiImplicitParam(name="除法测试",value="除法测试",required=true)
    public CommonResult testError (@Valid @PathVariable("value") Integer value) {
        if (value == 0) {
            Integer result = 1/value;
            log.info("result: " + result);
        }
        return CommonResult.success(value);
    }
}
