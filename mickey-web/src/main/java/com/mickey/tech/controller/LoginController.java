package com.mickey.tech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author suzhengxiao
 * @date 2020/7/24 7:52 上午
 */
@RestController
public class LoginController {
    /**
     * 登陆页面
     */
    @GetMapping("/su")
    public ModelAndView index() {
        return new ModelAndView("user/login");
    }
}
