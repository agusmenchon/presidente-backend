package org.presidenteapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // esta url llega a los controllers con @messageMapping
        registry.enableSimpleBroker("/topic"); //esta url llega a simpleBroker ???
    }


    @Override
    //aca es donde se conecta desde angular para consumir el servicio
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/presidente")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }
}
