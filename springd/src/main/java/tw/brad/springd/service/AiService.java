package tw.brad.springd.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import tw.brad.springd.dto.request.AskRequest;
import tw.brad.springd.dto.response.AskResponse;

import java.util.Base64;

@Service
public class AiService {
    @Autowired
    private ChatClient chatClient;

    public AskResponse analyze (AskRequest request){
        byte[] bytes = Base64.getDecoder().decode(request.base64());

        return chatClient.prompt()
                .system("""
                        你是影像判斷高手.
                        ACCEPT: 影像中有頭有肩膀, 臉部五官清晰.
                        REJECT: 只能有一個人, 不可以是動物,卡通, 物品,風景, 不雅, AI生成.
                        UNCERTAIN: 模糊, 太暗或其他因素無法判斷.
                        """)
                .user(user -> user.text("判斷這張影像資料是否符合人像大頭貼:")
                        .media(MimeTypeUtils.parseMimeType(
                                request.type()),
                                new ByteArrayResource(bytes)))
                .call()
                .entity(AskResponse.class);

    }



}
