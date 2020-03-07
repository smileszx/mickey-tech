package com.mickey.tech.controller;

import com.mickey.tech.common.core.util.CommonResult;
import com.mickey.tech.orm.entity.User;
import com.mickey.tech.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Redis 测试
 * @author suzhengxiao
 * @date 2020/3/7 15:46
 **/
@Slf4j
@RestController
@RequestMapping("/restful")
@Api(tags="Redis测试API")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "测试Redis set string")
    @GetMapping("/set")
    public CommonResult testRedis () throws Exception {
        String result = redisService.test("alibaba", "阿里巴巴");
        return CommonResult.success(result);
    }
}
