package tw.brad.spring8.controller;

import org.springframework.web.bind.annotation.*;
import tw.brad.spring8.dto.TextRequest;
import tw.brad.spring8.dto.TextResponse;
import tw.brad.spring8.service.AiTextService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AIController {

    private final AiTextService service;

    public AIController(AiTextService service){this.service = service;}

    @GetMapping("/active")
    public Map<String,String> active(){
        return Map.of("status", "Up");
    }

    @PostMapping("/ai/desc")
    public TextResponse descProduct(@RequestBody TextRequest request){
        return new TextResponse(service.descProduct(request.text()));
    }

    @PostMapping("/ai/complaint")
    public TextResponse complaint(@RequestBody TextRequest request){
        return new TextResponse(service.complaint(request.text()));
    }

}
