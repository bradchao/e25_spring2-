package tw.brad.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.brad.spring3.apis.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    JSON(application/json) <- RestController -> Service -> Repository -> Entity

    String or ResponseEntity.ok(物件) <- Controller -> Model(-> Service -> Repository -> Entity)

 */
@Controller
@RequestMapping("/")
public class WebController {
    /*
        prefix + viewName + suffix
        prefix: classpath:/templates/
        suffix: .html
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/member")
    public String memberIndex(){
        return "/member/index";
    }

    @RequestMapping("/page1")
    public String page1(Model model){
        model.addAttribute("companyName", "Brad Big Company");
        model.addAttribute("userName", "Brad");

        User user = new User();
        user.setId(1);
        user.setAge(18);
        user.setGender(false);
        user.setName("Vivi");
        model.addAttribute("user", user);

        String now = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        model.addAttribute("now", now);

        return "/page1";
    }



}
