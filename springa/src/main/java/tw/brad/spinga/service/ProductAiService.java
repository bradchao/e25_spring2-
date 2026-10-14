package tw.brad.spinga.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.brad.spinga.dto.response.AskResponse;
import tw.brad.spinga.tool.ProductTool;

@Service
public class ProductAiService {
    private final ChatClient chatClient;
    private final ProductService productService;

    public ProductAiService(ChatClient.Builder builder, ProductService productService){
        this.chatClient = builder.build();
        this.productService = productService;
    }

    public AskResponse ask(String text){
        ProductTool tool = new ProductTool(productService);

        var response= chatClient.prompt()
                .system("""
                        xxxx
                        """)
                .user(user -> user.text("").param("input", text) )
                .tools(tool)
                .call()
                .chatResponse();

        String answer = response.getResult().getOutput().getText();
        return new AskResponse(answer, "JSON", null);
    }







}
