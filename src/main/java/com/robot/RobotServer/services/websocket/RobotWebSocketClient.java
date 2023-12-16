package com.robot.RobotServer.services.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;

public class RobotWebSocketClient {
    public static void main(String[] args) throws Exception {
        WebSocketClient client = new StandardWebSocketClient();
        RobotWebSocketHandler robotWebSocketHandler = RobotWebSocketHandler.getInstance();
        robotWebSocketHandler.connect(client);
        Thread.sleep(100000);
    }
    public static class RobotWebSocketHandler implements WebSocketHandler {
        private static final RobotWebSocketHandler instance = new RobotWebSocketHandler();
        private RobotWebSocketHandler(){}
        public static RobotWebSocketHandler getInstance() {
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

            System.out.println("Received message:afterConnectionEstablished 连接建立后调用方法！");
            session.sendMessage(new TextMessage("test"));
        }

        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IllegalAccessException {
            if(session == this.session) {
//            handleTextMessage() - 处理文本消息
//            handleBinaryMessage() - 处理二进制消息
//            handlePongMessage() - 处理Pong消息
                System.out.println("handleMessage");
                System.out.println("Received message: " + message.getPayload());
            } else {
                throw new IllegalAccessException("Session not connected");
            }

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
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
            System.out.println("Received message:afterConnectionClosed 连接关闭后调用!");
            reconnectAsync();
        }

        @Override
        public boolean supportsPartialMessages() {
            System.out.println("Received message:supportsPartialMessages");
            return false;
        }

    }
}
