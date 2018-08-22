package com.cdut.b2p.modules.shop.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.cdut.b2p.modules.shop.websocket.handler.ChatWebSocketHandler;
import com.cdut.b2p.modules.shop.websocket.interceptor.HandshakeInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(),"/websocket/chat").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(webSocketHandler(), "/sockjs/chat").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }
 
    @Bean
    public ChatWebSocketHandler webSocketHandler(){
        return new ChatWebSocketHandler();
    }

}
