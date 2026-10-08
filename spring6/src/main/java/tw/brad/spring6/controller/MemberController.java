package tw.brad.spring6.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MemberController {
    @GetMapping("")
    public String root(){
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false, value = "error") String error,
                        @RequestParam(required = false, value = "logout") String logout,
                        Model model){
        if (error != null) model.addAttribute("error", "Login Failure");
        if (logout != null) model.addAttribute("logout", "Logout Success");

        return "login";
    }

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("companyName", "Brad Big Company");
        return "main";
    }

}
