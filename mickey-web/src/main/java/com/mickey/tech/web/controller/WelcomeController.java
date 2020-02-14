package com.mickey.tech.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class WelcomeController {

    @GetMapping("/")
    public String index(Model model){
        //后端传参至HTML页面
        model.addAttribute("title", "欢迎访问系统");
        return "index";
    }
}
