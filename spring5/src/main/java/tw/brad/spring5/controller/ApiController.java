package tw.brad.spring5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring5.annotation.CheckJwt;
import tw.brad.spring5.dto.Login;
import tw.brad.spring5.util.JwtToken;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(
            @RequestBody Login login){

        if (login.getAccount().equals("brad") && login.getPasswd().equals("123456")){
            // Login Success
            String data = String.format("%d:%s", 123, login.getAccount());
            String token = JwtToken.createToken(data);
            Map<String,Object> resp = Map.of(
                    "success", true,
                    "token", token
            );
            return ResponseEntity.ok(resp);
        }else{
            Map<String,Object> resp = Map.of(
                    "success", false
            );
            return ResponseEntity.ok(resp);
        }
    }

    @CheckJwt
    @RequestMapping("/main")
    public ResponseEntity<Map<String,Object>> main(){
        System.out.println("Data.....");
        return ResponseEntity.ok(Map.of("suceess",true, "data", "Member Only"));
    }


}
