package com.wp.domain.matching;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();
    private List<ChatMessage> msglist = new ArrayList<ChatMessage>();
    public static ChatRoom create(String Id){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = Id;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessage.getType() == MessageType.ENTER){
            sessions.add(session);
            for(ChatMessage cm:msglist){
                TextMessage textMessage = new TextMessage(objectMapper.
                        writeValueAsString(cm.getMessage()));
                session.sendMessage(textMessage);
            }
            chatMessage.setMessage(chatMessage.getWriter() + "님이 입장하셨습니다. (현재인원 : "+sessions.size()+")");
        }
        else if(chatMessage.getType() == MessageType.LEAVE){
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getWriter() + "님이 퇴장하셨습니다. (현재인원 : "+sessions.size()+")");
        }
        else{
            chatMessage.setMessage(chatMessage.getWriter() + " : " + chatMessage.getMessage());
        }
        msglist.add(chatMessage);
        send(chatMessage,objectMapper);
    }

    private void send(ChatMessage chatMessage, ObjectMapper objectMapper) throws IOException, IOException {
        TextMessage textMessage = new TextMessage(objectMapper.
                writeValueAsString(chatMessage.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }
}