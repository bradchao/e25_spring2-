package tw.brad.spring7.websocket;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import tw.brad.spring7.util.JwtToken;

import java.security.Principal;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");

    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        registry.addEndpoint("/ws-chat")
                .addInterceptors(new HandshakeInterceptor() {
                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request,
                                                   ServerHttpResponse response,
                                                   WebSocketHandler wsHandler,
                                                   Map<String, Object> attributes) throws Exception {
                        /*
                        if (request instanceof ServletServerHttpRequest req){
                            HttpServletRequest httpReq = req.getServletRequest();
                            String tokenParam = httpReq.getParameter("token");
                            if (tokenParam != null && tokenParam.startsWith("Bearer ")){
                                String token = tokenParam.split(" ")[1];
                                String account = JwtToken.parseToken(token);
                                attributes.put("account", account);
                                return true;
                            }
                        }
                         */

                        return true;
                    }

                    @Override
                    public void afterHandshake(ServerHttpRequest request,
                                               ServerHttpResponse response,
                                               WebSocketHandler wsHandler, @Nullable Exception exception) {

                    }
                })
                .setHandshakeHandler(new DefaultHandshakeHandler(){
                    @Override
                    protected @Nullable Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        return super.determineUser(request, wsHandler, attributes);
                    }
                })
                .setAllowedOriginPatterns("*")
                .withSockJS();

    }

}