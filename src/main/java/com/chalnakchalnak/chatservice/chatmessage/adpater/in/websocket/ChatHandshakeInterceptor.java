package com.chalnakchalnak.chatservice.chatmessage.adpater.in.websocket;

import com.chalnakchalnak.chatservice.chatroom.application.port.out.validator.ChatRoomValidatorPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatHandshakeInterceptor implements HandshakeInterceptor {

    private final ChatRoomValidatorPort chatRoomValidator;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {

        if (!(request instanceof ServletServerHttpRequest)) return false;

        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpServletRequest httpRequest = servletRequest.getServletRequest();

        String userUuid = httpRequest.getParameter("userUuid");
        String roomUuid = httpRequest.getParameter("roomUuid");

        if (userUuid == null || roomUuid == null) {
            log.warn("WebSocket 연결 거부: 파라미터 없음");
            return false;
        }

        chatRoomValidator.memberAccessed(roomUuid, userUuid);

        // 검증 통과
        attributes.put("userUuid", userUuid);
        attributes.put("roomUuid", roomUuid);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
        log.info("WebSocket 연결");
    }
}

