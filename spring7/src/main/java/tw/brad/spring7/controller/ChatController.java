package tw.brad.spring7.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tw.brad.spring7.response.ChatMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatController {

    @MessageMapping("/chat/send")
    @SendTo("/topic/public")
    public ChatMessage chatMessage(String content){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(content);
        chatMessage.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        return chatMessage;
    }
}
