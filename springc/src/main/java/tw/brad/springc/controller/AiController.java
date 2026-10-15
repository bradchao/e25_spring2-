package tw.brad.springc.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.springc.dto.request.AskRequest;
import tw.brad.springc.dto.response.AskResponse;
import tw.brad.springc.service.AiService;

@RestController
@RequestMapping("/ai")
public class AiController {
    @Autowired
    private AiService aiService;

    @PostMapping("/ask")
    public AskResponse ask(@Valid @RequestBody AskRequest request){
        return aiService.ask(request.text());
    }
}
