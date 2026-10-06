package tw.brad.spring4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring4.dto.Login;
import tw.brad.spring4.repo.MemberRepo;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private MemberRepo repo;

    @PostMapping("/login")
    public void login(@RequestBody Login login){


    }
}
