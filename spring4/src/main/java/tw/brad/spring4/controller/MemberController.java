package tw.brad.spring4.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring4.entity.Member;
import tw.brad.spring4.repo.MemberRepo;
import tw.brad.spring4.util.JwtToken;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/members")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {
    @Autowired
    private MemberRepo repo;

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody Member body){
        Member member = repo.findByAccount(body.getAccount()).orElse(null);
        if (member != null && BCrypt.checkpw(body.getPasswd(), member.getPasswd())){
            Map<String,Object> map = new HashMap<>();
            map.put("success", true);
            map.put("member", Map.of(
                    "id", member.getId(),
                    "account", member.getAccount(),
                    "name", member.getName()));
            map.put("token", JwtToken.createToken(member.getAccount()));
            return ResponseEntity.ok(map);
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
        }
    }

}
