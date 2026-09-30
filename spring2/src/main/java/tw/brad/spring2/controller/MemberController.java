package tw.brad.spring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring2.entity.Info;
import tw.brad.spring2.entity.Member;
import tw.brad.spring2.service.MemberService;

import java.util.Map;

@RequestMapping("/members")
@RestController
public class MemberController {
    @Autowired
    private MemberService service;

    /*
        POST: /members
        data: {
            account: "xxx",
            passwd: "xxx",
            info: {
                tel: "xxx",
                name: "xxx",
                gender: true/false
            }
        }
     */
    @PostMapping("")
    public ResponseEntity<Member> addMember(@RequestBody Map<String,Object> data){
        Member member = new Member();
        member.setAccount((String)data.get("account"));
        member.setPasswd((String)data.get("passwd"));

        Info info = null;
        Map<String,Object> infoData = (Map<String,Object>)data.get("info");
        if (infoData != null){
            info = new Info();
            info.setIsMale((Boolean)infoData.get("gender"));
            info.setTel((String)infoData.get("tel"));
            info.setName((String)infoData.get("name"));
        }

        Member savedMember = service.save(member, info);

        return ResponseEntity.ok(savedMember);
    }

    @PutMapping("/{memberId}/info")
    public ResponseEntity<Info> setInfoToMember(@PathVariable Long memberId,
                                                @RequestBody Map<String,Object> data){
        Info info = new Info();
        info.setIsMale((Boolean)data.get("gender"));
        info.setTel((String)data.get("tel"));
        info.setName((String)data.get("name"));

        Info savedInfo = service.saveInfoToMember(memberId, info);
        return ResponseEntity.ok(savedInfo);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> queryMember(@PathVariable Long memberId){
        return ResponseEntity.ok(service.findMemberById(memberId));

    }


}
