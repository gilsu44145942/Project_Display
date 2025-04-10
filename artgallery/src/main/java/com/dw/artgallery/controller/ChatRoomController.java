package com.dw.artgallery.controller;

import com.dw.artgallery.model.ChatRoom;
import com.dw.artgallery.service.ChatMessageService;
import com.dw.artgallery.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{artistId}")
    public ResponseEntity<Long> createOrGetRoom(
            @PathVariable String artistId,
            Authentication authentication
    ) {
        String userId = authentication.get
    }
}
