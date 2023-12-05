package com.robot.RobotServer.services.websocket;

public class WsGreeting {
    private String content;
    public WsGreeting(){}
    public WsGreeting(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
