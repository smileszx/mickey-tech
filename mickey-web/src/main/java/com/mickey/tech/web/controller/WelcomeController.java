package com.mickey.tech.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 欢迎页面
 * 当前类不能用@RestController，@RestController注解相当于@ResponseBody和@Controller的结合
 * 无法跳转返回templates中的页面
 * @author suzhengxiao
 * @date 2020/2/10 21:53
 **/
@Controller
@Slf4j
@RequestMapping("/v")
public class WelcomeController {

    @GetMapping("/hello")
    public String index(){
        return "index";
    }
}
