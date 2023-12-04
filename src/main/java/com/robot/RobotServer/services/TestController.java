package com.robot.RobotServer.services;

import com.robot.RobotServer.db.mysql.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @PostMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(path = "/test1")
    public @ResponseBody String test1 (@RequestParam String username){
        System.out.println(username);
        return "Saved";
    }
}
