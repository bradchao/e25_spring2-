package tw.brad.springc.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.brad.springc.dto.response.AskResponse;

@Service
public class AiService {
    @Autowired
    private ChatClient chatClient;

    @Autowired
    private DirectoryService directory;

    @Autowired
    private OrderQueryService orders;

    public AskResponse ask(String text){

    }







}
