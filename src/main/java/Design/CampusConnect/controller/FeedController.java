package Design.CampusConnect.controller;

import Design.CampusConnect.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedController {

    @Autowired
    PostService service;

    @RequestMapping(value = "/feed")
    public String messages(Model model) {
        model.addAttribute("feed", service.getAllPosts());
        return "mainfeed";
    }
}
