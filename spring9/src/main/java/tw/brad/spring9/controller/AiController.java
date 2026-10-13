package tw.brad.spring9.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring9.dto.request.TestRequest;
import tw.brad.spring9.dto.response.ProductCriteria;
import tw.brad.spring9.dto.response.Result;
import tw.brad.spring9.service.StructuredAiService;

@RestController
@RequestMapping("/ai")
public class AiController {
    private final StructuredAiService service;
    public AiController(StructuredAiService service){this.service = service;}

    @PostMapping("/product")
    public Result<ProductCriteria> parse(@Valid @RequestBody TestRequest request){
        return new Result<>(service.parseProductCriteria(request.text()));
    }


}
