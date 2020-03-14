package com.mickey.tech.controller;

import com.mickey.tech.common.core.util.CommonResult;
import com.mickey.tech.orm.entity.User;
import com.mickey.tech.service.TestService;
import com.mickey.tech.vo.SwaggerVO;
import com.mickey.tech.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Swagger文档生成测试
 * @author suzhengxiao
 * @date 2020/1/10 16:04
 **/
@Slf4j
@RestController
@RequestMapping("/restful")
@Api(tags="Swagger测试API")
public class SwaggerController {

    @Autowired
    private TestService testService;

    @ApiOperation(value="查询用户值")
    @GetMapping("/user/{id}")
    @ApiImplicitParam(name="id",value="用户id",required=true)
    public CommonResult sayHello (@Valid @PathVariable("id") Long id, HttpServletRequest request) {
        if (Objects.nonNull(request.getSession())) {
            request.getSession().setAttribute("username", "mickey");
            request.getSession().setAttribute("password", "fhaifuahgasdjaiwuefhqwesdfkjah");
            log.info("Session Id : {}", request.getSession().getId());
        }
        User user = testService.findUserById(id);
        log.info(user.toString());
        return CommonResult.success(user);
    }

    @ApiOperation(value = "参数测试")
    @PostMapping("/param")
    public CommonResult testParam (@Valid @RequestBody SwaggerVO swaggerVO) {
        return CommonResult.success(swaggerVO);
    }

    @ApiOperation(value = "测试事务")
    @GetMapping("/tx")
    public CommonResult testTransaction () throws Exception {
        testService.insert(new User());
        return CommonResult.error();
    }

    @ApiOperation(value = "参数测试")
    @PostMapping("/user")
    public CommonResult testParam (@Valid @RequestBody UserInfoVO userInfoVO) throws Exception{
        int i =1/0;
        System.out.println(i);
        return CommonResult.success(userInfoVO);
    }
}
