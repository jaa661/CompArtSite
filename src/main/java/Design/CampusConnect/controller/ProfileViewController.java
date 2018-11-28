package Design.CampusConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ProfileViewController {
    @RequestMapping("/Profile")
    public String profile(Principal pricipal, Model model) {
        model.addAttribute("username", pricipal.getName());
        System.out.println("hitting prof");
        return "Profile";
    }

    @RequestMapping("/adduser")
    public String adduser(Model model) {
        return "AddUserForm";
    }

    @RequestMapping("/addpost")
    public String addpost(Model model) {
        return "PostMessageToGroup";
    }
}
