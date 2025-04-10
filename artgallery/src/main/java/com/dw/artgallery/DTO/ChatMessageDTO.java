package com.dw.artgallery.DTO;

import com.dw.artgallery.chat.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDTO {
    private MessageType type;
    private String content;
    private String sender;
    private String receiver;
}
