package com.mickey.tech.interceptor;

import com.mickey.tech.common.core.annotation.SpringHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器
 * @author suzhengxiao
 * @date 2020/1/15 10:40
 **/
@Slf4j
@SpringHandlerInterceptor(excludePatterns = {"/static/*"}, includePatterns = "/*", order = 1)
public class LogInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求全局唯一ID
     */
    private final static String REQUEST_ID = "requestId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("requestStartTime", System.currentTimeMillis());
        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestId);
        log.info("加入requestId={}，请求地址={}", requestId, request.getRequestURL());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        Long requestStartTime = (Long) request.getAttribute("requestStartTime");
        String requestId = MDC.get(REQUEST_ID);
        log.info("清除requestId={}，耗时={}", requestId, System.currentTimeMillis() - requestStartTime);
        MDC.remove(REQUEST_ID);
    }
}
