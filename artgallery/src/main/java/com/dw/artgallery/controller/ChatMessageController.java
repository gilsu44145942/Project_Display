package com.dw.artgallery.controller;

import com.dw.artgallery.DTO.ChatMessageDTO;
import com.dw.artgallery.model.ChatRoom;
import com.dw.artgallery.model.ChatMessage;
import com.dw.artgallery.service.ChatRoomService;
import com.dw.artgallery.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatMessageController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send") // 클라이언트 → /app/chat.send 로 전송
    public void sendMessage(@Payload ChatMessageDTO messageDTO) {

        // 1. 채팅방 조회 or 생성
        ChatRoom chatRoom = chatRoomService.getOrCreateRoom(messageDTO.getSender(), messageDTO.getReceiver());

        // 2. 메시지 저장
        ChatMessage saved = chatMessageService.saveMessage(messageDTO, chatRoom);

        // 3. 상대 유저에게 메시지 전송 (WebSocket 전용 queue)
        messagingTemplate.convertAndSendToUser(
                messageDTO.getReceiver(), // 대상 ID
                "/queue/messages",        // 목적지
                messageDTO                // payload 그대로
        );
    }
}
