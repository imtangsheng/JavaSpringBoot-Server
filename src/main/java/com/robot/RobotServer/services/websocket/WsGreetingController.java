package com.robot.RobotServer.services.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WsGreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public WsGreeting wsGreeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new WsGreeting("Hello,"+ HtmlUtils.htmlEscape(message.getName())+"!");
    }
}
