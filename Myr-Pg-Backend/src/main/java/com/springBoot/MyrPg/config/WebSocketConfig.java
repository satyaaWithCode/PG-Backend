package com.springBoot.MyrPg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");//it will subscribe to topic and   /topic only sends messages to clients. It does not “consume” messages from clients.
        config.setApplicationDestinationPrefixes("/app");//handle server side messages mean produce all messages here
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")//call from ui using this end point
                .setAllowedOrigins("https://satyaa-pg.netlify.app")//ui url
                .withSockJS();//
    }
}
