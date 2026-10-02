package tw.brad.spring3.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.brad.spring3.entity.Hotel;
import tw.brad.spring3.entity.Member;
import tw.brad.spring3.repo.HotelRepo;

import java.util.Base64;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HotelRepo repo;

    @GetMapping("")
    public String home(HttpSession session,
                       Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size){
        Object obj = session.getAttribute("member");
        if (obj == null) return "redirect:/members/login";

        Member member = (Member)obj;
        model.addAttribute("member", member);
        if (member.getIcon() != null){
            String iconString = "data:image/*; base64, " +
                    Base64.getEncoder().encodeToString(member.getIcon());
            model.addAttribute("icon", iconString);
        }else {
            model.addAttribute("icon", "");
        }
        //----------------------
        Pageable pageable = PageRequest.of(page, size);
        Page<Hotel> hotelPage = repo.findAll(pageable);
        model.addAttribute("hotels", hotelPage);

        return "home";
    }

}
