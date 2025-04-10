package com.dw.artgallery.service;

import com.dw.artgallery.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
    @Autowired
    ChatMessageRepository chatMessageRepository;
}
