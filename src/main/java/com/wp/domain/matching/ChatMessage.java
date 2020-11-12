package com.wp.domain.matching;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    private String id;
    private String writer;
    private String message;
    private MessageType type;
}
enum MessageType {
    ENTER,CHAT,LEAVE
}