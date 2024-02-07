package com.robot.RobotServer.services.websocket;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RobotWebSocketClient {

    private static final Logger log = LoggerFactory.getLogger(RobotWebSocketClient.class);

    public static void main(String[] args) throws Exception {
        log.error("RobotWebSocketClient测试用例");
        WebSocketClient client = new StandardWebSocketClient();
        new RobotWebSocketHandler();
        RobotWebSocketHandler robotWebSocketHandler = RobotWebSocketHandler.getInstance();
        robotWebSocketHandler.connect(client);
        Thread.sleep(100000);
    }
//    public static class RobotWebSocketHandler implements WebSocketHandler {
    @Component
    public static class RobotWebSocketHandler extends AbstractWebSocketHandler {
        private static RobotWebSocketHandler instance;
        private RobotWebSocketHandler(){}
        public static RobotWebSocketHandler getInstance() {
            if (instance == null) {
                instance = new RobotWebSocketHandler();
            }
            return instance;
        }

    public String wsUrl = "ws://localhost:9090";
        WebSocketClient client;
        public WebSocketSession session;
        public void connect(WebSocketClient client) {
            this.client = client;
            try {
                session = client.execute(this,wsUrl).get();
            } catch (Exception e){
                // 捕获异常信息
                String message = e.getMessage();
                System.out.println(message);
                System.out.println("Received message:afterConnectionEstablished 连接失败！");
            }
        }

        public void reconnectAsync() throws Exception {
            boolean isConnected = false;
            while (!isConnected){
                for (int i = 0;i<30;i++){
                    connect(client);
                    Thread.sleep(1000);
                    if (session != null && session.isOpen()) {
                        isConnected = true;
                        break;
                    }
                }
                while (!isConnected) {
                    Thread.sleep(30000);
                    connect(client);
                    if (session != null && session.isOpen()) {
                        isConnected = true;
                        break;
                    }
                }
            }
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {

            System.out.println("Received message:afterConnectionEstablished 连接建立后调用方法");
            log.info("连接建立后调用方法！");
            session.sendMessage(new TextMessage("test"));
        }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info("Received message:handleTransportError:" + message.getPayload());
        System.out.println("Received message:handleTransportError:" + message.getPayload());
    }

    public Boolean sendMessage(String msg) {
            if(this.session == null) {
                return false;
            }
            try {
                this.session.sendMessage(new TextMessage(msg));
                return true;
            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println(message);
                return false;
            }
        }

        @Override
        public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
            System.out.println("Received message:handleTransportError");
            log.error("handleTransportError");
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
            System.out.println("Received message:afterConnectionClosed 连接关闭后调用!");
            log.warn("afterConnectionClosed连接关闭");
            reconnectAsync();
        }

        @Override
        public boolean supportsPartialMessages() {
            System.out.println("Received message:supportsPartialMessages");
            return false;
        }

        @Scheduled(fixedRate = 5000) // 每5秒执行一次
        public void task1() {
            System.out.println("定时任务1执行了");
            // 执行具体的定时任务逻辑
        }


    }
}
