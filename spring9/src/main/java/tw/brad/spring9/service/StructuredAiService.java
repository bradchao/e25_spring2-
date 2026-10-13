package tw.brad.spring9.service;

import jakarta.validation.Validator;
import org.springframework.ai.chat.client.ChatClient;
import tw.brad.spring9.dto.response.ProductCriteria;

public class StructuredAiService {
    private final ChatClient chatClient;
    private final Validator validator;

    public StructuredAiService(ChatClient.Builder builder,
                               Validator validator){
        this.chatClient = builder.build();
        this.validator = validator;
    }

    public ProductCriteria parseProductCriteria(String text){
        return null;
    }


    private <T> T generate(String instruction, String text, Class<T> type){
        try {
            return chatClient.prompt()
                    .system(instruction)
                    .user(user -> user.text("分析資料:{input}").param("input", text))
                    .call()
                    .content();
        }
    }




}
