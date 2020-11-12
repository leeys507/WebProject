package com.wp.domain.matching;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap=new LinkedHashMap<String,ChatRoom>();

    public ChatRoom findRoomById(String bno){
        return chatRoomMap.get(bno);
    }

    public ChatRoom createChatRoom(String bno){
        ChatRoom chatRoom = ChatRoom.create(bno);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
    public void deleteChatRoom(String bno){
        chatRoomMap.remove(bno);
    }
}
