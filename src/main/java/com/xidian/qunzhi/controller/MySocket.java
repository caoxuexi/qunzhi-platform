package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.service.impl.AdminUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Cao Study
 * @description <h1>MySocket</h1>
 * @date 2021-10-27 12:50
 */
@Component
@ServerEndpoint("/websocket")
public class MySocket {
    private static final Logger LOG = LoggerFactory.getLogger(MySocket.class);
    /**
     * 初始在线人数
     */
    private static int online_num = 0;
    /**
     * 线程安全的socket集合
     */
    private static CopyOnWriteArraySet<MySocket> webSocketSet = new CopyOnWriteArraySet<MySocket>();
    /**
     * 会话
     */
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        LOG.info("有链接加入，当前人数为:"+getOnline_num());
        this.session.getAsyncRemote().sendText("当前人数为:"+getOnline_num());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for(MySocket item:webSocketSet){
            this.session.getAsyncRemote().sendText(message);
        }
    }
    public synchronized int getOnline_num(){
        return MySocket.online_num;
    }
    public synchronized int subOnlineCount(){
        return MySocket.online_num--;
    }
    public synchronized int addOnlineCount(){
        return MySocket.online_num++;
    }
}
