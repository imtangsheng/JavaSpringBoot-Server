package com.robot.RobotServer.services.tcp;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class TCPServer {

    @Value("${tcp.port}")
    private Integer tcpPort = 31024;

    ServerSocket serverSocket = new ServerSocket(tcpPort);

    public TCPServer() throws IOException {
    }

    @PostConstruct
    public void run() {

        System.out.println(tcpPort);
//        while (true) {
//            Socket socket = serverSocket.accept(); //
//            System.out.println(socket);
//        }
    }
}
