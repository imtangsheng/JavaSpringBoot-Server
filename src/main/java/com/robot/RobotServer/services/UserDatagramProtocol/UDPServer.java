package com.robot.RobotServer.services.UserDatagramProtocol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.messaging.Message;

import java.util.Arrays;


@Configuration
public class UDPServer {
    @Value("${udp.port}")
    private  int port;

    @Bean
    public IntegrationFlow udpIn(){
        System.out.println("udp server");
        System.out.println(port);
        return IntegrationFlow.from(Udp.inboundAdapter(port))
                .channel("udpReceiveChannel")
                .get();
    }

    @Bean
    public DirectChannel udpReceiveChannel(){

        System.out.println(port);
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "udpReceiveChannel")
    public void udpHandleMessage(Message<byte[]>message) {

        byte[] payload = message.getPayload();
        System.out.println(Arrays.toString(payload));
    }
}
