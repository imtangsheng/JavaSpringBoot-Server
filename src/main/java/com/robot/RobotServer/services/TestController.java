package com.robot.RobotServer.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
