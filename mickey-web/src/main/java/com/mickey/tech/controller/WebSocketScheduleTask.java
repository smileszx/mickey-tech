package com.mickey.tech.controller;

import com.mickey.tech.common.websocket.WebSocketServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description:webSocket定时发送消息类
 * @statement: 以小于60s的频率发送给websocket连接的对象，防止反向代理的60s超时限制
 */
@Configuration
@EnableScheduling
public class WebSocketScheduleTask {


    //@Scheduled(cron = "0/10 * * * * ?")
    @Scheduled(fixedRate= 10_000)
    private void configureTasks() throws Exception{
        WebSocketServer.send("Hello World!");
    }
}