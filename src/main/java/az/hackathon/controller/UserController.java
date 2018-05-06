package az.hackathon.controller;

import az.hackathon.model.User;
import az.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUserByFin")
    public String getUserByFin(@RequestParam("fin") String fin, Model model){
        User user = userService.getUserByFin(fin);
        int isReturning = 1;

        if (user == null){
            user = new User();
            user.setFullName("");
            user.setAddress("");
            user.setContactNumber("");
            user.setEmail("");
            isReturning = 0;
        }

        model.addAttribute("user", user);
        model.addAttribute("isReturning", isReturning);
        return "/view_elements/user_details";

    }

}
