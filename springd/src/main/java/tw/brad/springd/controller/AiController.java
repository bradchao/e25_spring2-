package tw.brad.springd.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.springd.dto.request.AskRequest;
import tw.brad.springd.dto.response.AskResponse;
import tw.brad.springd.service.AiService;

@RequestMapping("/ai")
@RestController
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/ask")
    public AskResponse ai(@Valid @RequestBody AskRequest request){
        return aiService.analyze(request);
    }

}
