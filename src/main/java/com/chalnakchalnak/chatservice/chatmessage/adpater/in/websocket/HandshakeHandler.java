package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class HandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler webSocketHandler,
                                      Map<String, Object> attributes) {

        String memberUuid = ((ServletServerHttpRequest) request).getServletRequest().getParameter("memberUuid");

        return () -> memberUuid;
    }
}
