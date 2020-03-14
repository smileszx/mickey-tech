package com.mickey.tech.common.websocket.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.mickey.tech.common.core.util.ThreadLocalUtil;
import com.mickey.tech.common.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author suzhengxiao
 * @date 2020/3/13 0:28
 **/
@EnableScheduling
@Component
@Slf4j
public class WebSocketTask {

    /**
     * 每10秒执行一次
     */
//    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledTaskByCorn() throws IOException {
        WebSocketServer.BroadCastInfo(Thread.currentThread().getName() + ", " + UUID.randomUUID());
        log.info("定时任务开始 ByCorn：" + DateUtil.now());
        Map<String, Object> map = ThreadLocalUtil.getContextTreadLocal();
        if (MapUtil.isEmpty(map)) {
            log.warn("========== ThreadLocal 获取map值为空,{}", map);
        } else {
            map.forEach((k, v) -> {
                try {
                    WebSocketServer.SendMessage(k, UUID.randomUUID().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info("WebSocket send message : {}, sessionId: {}", UUID.randomUUID().toString(), k);
            });
        }

        log.info("定时任务结束 ByCorn：" + DateUtil.now());
    }
}
