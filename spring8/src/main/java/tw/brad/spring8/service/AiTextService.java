package tw.brad.spring8.service;

import kotlin.contracts.ReturnsNotNull;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiTextService {
    private final ChatClient chatClient;

    public AiTextService(ChatClient.Builder builder){ chatClient = builder.build();}

    public String descProduct(String text){
        return generate("""
                你是商品助理, 使用繁體中文, 依照使用者提供的商品資料來
                回覆約 80 到 120 字的商品介紹.
                直接輸出文字內容, 不要 json
                """, text);
    }

    public String complaint(String text){
        return generate("""
                你是客服專員, 使用繁體中文, 依照使用者客訴整理出以下重點:
                1. 發生的問題
                2. 客戶訴求
                3. 需要確認的資料
                不要承諾退款, 賠償或是出貨時間等等
                直接輸出文字內容,不要 json
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
            return "ERROR:" + e;
        }
    }



}
