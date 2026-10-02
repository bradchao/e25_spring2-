package tw.brad.spring3.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.brad.spring3.dto.MemberForm;
import tw.brad.spring3.entity.Member;
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
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", "Account EXIST!");
            return "register";
        }
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }


}
