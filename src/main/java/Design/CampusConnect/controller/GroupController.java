package Design.CampusConnect.controller;

import Design.CampusConnect.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroupController {

    @Autowired
    PostService service;

    @RequestMapping(value = "/group/{groupId}")
    public String messages(Model model,@PathVariable int groupId) {
        model.addAttribute("feed", service.getPostsByGroupId(groupId));
        return "mainfeed";
    }
}
