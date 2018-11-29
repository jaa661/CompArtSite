package Design.CampusConnect.controller;

import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class GroupController {

    @Autowired
    PostService service;

    @Autowired
    UserService Userservice;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/group/{groupId}")
    public String messages(Principal principal, Model model, @PathVariable int groupId) {
        model.addAttribute("user", Userservice.findByName(principal.getName()));
        model.addAttribute("feed", service.getPostsByGroupId(groupId));
        return "mainfeed";
    }

//    @RequestMapping(value = "/group/all)")
//    public String allGroups(Model model) {
//        System.out.println("hitting allGroups");
//        model.addAttribute("allGroups", groupService.getAllGroups());
//        return "Profile";
//    }

}
