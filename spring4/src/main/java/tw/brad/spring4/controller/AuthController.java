package tw.brad.spring4.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring4.dto.Login;
import tw.brad.spring4.entity.Member;
import tw.brad.spring4.repo.MemberRepo;
import tw.brad.spring4.reponse.LoginResponse;
import tw.brad.spring4.util.JwtToken;

import java.util.Map;
import java.util.Set;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private MemberRepo repo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login){
        Member member = repo.findByAccount(login.getAccount()).orElse(null);
        if (member == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("帳號錯誤");
        }
        if (!BCrypt.checkpw(login.getPasswd(), member.getPasswd())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密碼錯誤");
        }
        String token = JwtToken.createToken(
                member.getId() + ":" + member.getAccount());
        return ResponseEntity.ok(
                new LoginResponse(token, member.getAccount(), member.getName()));
    }

    @PostMapping("/api/test1")
    public ResponseEntity<String> test1(@RequestHeader String Authorization){
        System.out.println(Authorization);
        String subject = JwtToken.parseToken(Authorization.split(" ")[1]);
        return ResponseEntity.ok(subject);
    }


}
