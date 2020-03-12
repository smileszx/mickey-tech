package com.mickey.tech.controller;

import com.mickey.tech.common.core.util.CommonResult;
import com.mickey.tech.common.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {


    /**
     * 群发消息内容
     * @param message
     * @return
     */
    @RequestMapping(value="/sendAll", method= RequestMethod.GET)
    public CommonResult sendAllMessage(@RequestParam(required = true) String message){
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            log.error("群发消息异常", e);
        }
        return CommonResult.success();
    }
    
    /**
     * 指定会话ID发消息
     * @param message 消息内容
     * @param id 连接会话ID
     * @return
     */
    @RequestMapping(value="/sendOne", method=RequestMethod.GET)
    public CommonResult sendOneMessage(@RequestParam(required=true) String message, @RequestParam(required=true) String id){
        try {
            WebSocketServer.SendMessage(message, id);
        } catch (IOException e) {
            log.error("指定给SessionID发消息异常", e);
        }
        return CommonResult.success();
    }
}