package tw.brad.spring8.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiTextService {
    private final ChatClient chatClient;

    public AiTextService(ChatClient.Builder builder){ chatClient = builder.build();}

    public String descProduct(String text){
        return generate("""
                
                """, text);
    }

    public String complaint(String text){
        return generate("""
                
                """, text);
    }

    public String generate(String promt, String text){
        try {
            String result = chatClient
                    .prompt()
                    .system(promt)
                    .user(text)
                    .call()
                    .content();
            if (result == null || result.isBlank()){
                throw new IllegalStateException("Model Response ERROR");
            }
            return result;
        }catch (RuntimeException e){

        }
    }



}
