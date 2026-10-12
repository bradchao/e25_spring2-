package tw.brad.spring8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AIController {
    @GetMapping("/active")
    public Map<String,String> active(){
        return Map.of("status", "Up");
    }




}
