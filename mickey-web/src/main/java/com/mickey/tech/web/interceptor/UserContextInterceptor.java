package com.mickey.tech.web.interceptor;

import cn.hutool.system.UserInfo;
import com.mickey.tech.common.core.annotation.SpringHandlerInterceptor;
import com.mickey.tech.common.core.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息
 * @author suzhengxiao
 * @date 2020/2/10 15:34
 **/
@Slf4j
@SpringHandlerInterceptor(excludePatterns = {"/static/*"}, order = 1)
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo = new UserInfo();
        ThreadLocalUtil.set("userInfo", userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
