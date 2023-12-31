package com.robot.RobotServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
public class RobotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotServerApplication.class, args);
	}
	@GetMapping("/hello1")
	public String hello(@RequestParam(value = "name",defaultValue = "World!") String name){
		return String.format("Hello %s,\"/hello\"",name);
	}

}
