package com.mickey.tech.web.controller;

import com.mickey.tech.common.core.util.CommonResult;
import com.mickey.tech.web.vo.SwaggerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Swagger文档生成测试
 * @author suzhengxiao
 * @date 2020/1/10 16:04
 **/
@RestController
@RequestMapping("/swagger")
@Api(tags="Swagger测试API")
public class SwaggerController {

    @ApiOperation(value="问候世界")
    @GetMapping("/hi/{msg}")
    @ApiImplicitParam(name="msg",value="问候信息",required=true)
    public CommonResult sayHello (@Valid @PathVariable("msg") String msg) {
        return CommonResult.success(msg);
    }

    @ApiOperation(value = "参数测试")
    @PostMapping("/param")
    public CommonResult testParam (@Valid @RequestBody SwaggerVO swaggerVO) {
        return CommonResult.success(swaggerVO);
    }

}
