package tw.brad.spring7.response;

import lombok.Data;

@Data
public class ChatMessage {
    private String account, content, time;
}
