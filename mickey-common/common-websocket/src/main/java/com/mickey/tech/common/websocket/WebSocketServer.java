package com.mickey.tech.common.websocket;

import com.mickey.tech.common.core.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

// https://www.js.com/p/2c9be4641d43
@ServerEndpoint("/websocket/asset")
@Component
@Slf4j
public class WebSocketServer {

    @PostConstruct
    public void init () {
        log.info("websocket loading ...");
    }

    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    /**
     * 打开连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionSet.add(session);
        log.info("=========>>>>>>>>>>>>ThreadLocal set-----, current: {}", Thread.currentThread().getName());

//        ThreadLocalUtil.setContext(session.getId(), session);
//        Map<String, Object> threadLocal = ThreadLocalUtil.getContextTreadLocal();
//        log.info("=========>>>>>>>>>>>>ThreadLocal session: {}", threadLocal.get(session.getId()));
        int count = ONLINE_COUNT.incrementAndGet();
        log.info("新连接创建成功，当前连接数: {}", count);

        SendMessage(session, "连接成功");
    }

    /**
     * 收到客户端消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自客户端的新信息: {}", message);

        SendMessage(session, "收到消息,消息内容: {}" + message);
    }

    /**
     * 异常处理
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发生异常: {}, Session ID: {}", throwable.getMessage(), session.getId(), throwable);
    }

    @OnClose
    public void onClose (Session session) {

        sessionSet.remove(session);
        int count = ONLINE_COUNT.decrementAndGet();

        log.info("发现有连接关闭，当前连接数为：{}", count);
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {
        for (Session session : sessionSet) {
            if(session.isOpen()){
                SendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : sessionSet) {
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }
        if(session!=null){
            SendMessage(session, message);
        }
        else{
            log.warn("没有找到指定ID的会话：{}",sessionId);
        }
    }

}