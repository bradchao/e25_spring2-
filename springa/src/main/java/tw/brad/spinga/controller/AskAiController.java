package tw.brad.spinga.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spinga.dto.request.AskRequest;
import tw.brad.spinga.dto.response.AskResponse;
import tw.brad.spinga.service.ProductAiService;

@RestController
@RequestMapping("/ai")
public class AskAiController {
    @Autowired
    private ProductAiService service;

    @PostMapping("/product")
    public AskResponse ask(@Valid @RequestBody AskRequest request){
        System.out.println(request.text());
        return service.ask(request.text());
    }



}
