package Design.CampusConnect.controller;

import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class FeedController {

    @Autowired
    PostService service;

    @Autowired
    UserService Userservice;

    @Autowired
    GroupService Groupservice;

    @RequestMapping(value = "/feed")
    public String messages(Model model, Principal principal) {
        model.addAttribute("user", Userservice.findByName(principal.getName()));
        //model.addAttribute("group", Groupservice.findById());
        model.addAttribute("feed", service.getAllPosts());
        return "mainfeed";
    }
}
