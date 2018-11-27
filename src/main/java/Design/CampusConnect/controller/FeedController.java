package Design.CampusConnect.controller;

import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedController {

    @Autowired
    PostService service;

    @Autowired
    UserService Userservice;

    @RequestMapping(value = "/feed")
    public String messages(Model model) {
        model.addAttribute("feed", service.getAllPosts());
        return "mainfeed";
    }
}
