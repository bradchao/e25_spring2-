package tw.brad.spring3.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring3.dto.MemberForm;
import tw.brad.spring3.entity.Member;
import tw.brad.spring3.exception.MemberAccountExistException;
import tw.brad.spring3.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("/register")
    public String register(Model model){
        MemberForm form = new MemberForm();
        model.addAttribute("memberForm", form);
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(Model model,
                             @ModelAttribute @Valid MemberForm memberForm,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }

        try {
            Member member = service.register(memberForm);
            return "redirect:/members/login";
        }catch(MemberAccountExistException e){
            model.addAttribute("error", "Account EXIST!");
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "register";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String account,
                          @RequestParam String passwd,
                          Model model,
                          HttpSession session){

        Member member = service.login(account, passwd);
        if (member != null){
            session.setAttribute("member", member);
            return "redirect:/home";
        }
        return "redirect:/members/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/members/login";
    }

}
