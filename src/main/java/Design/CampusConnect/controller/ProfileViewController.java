package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ProfileViewController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserService Userservice;

    @RequestMapping("/Profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
//        model.addAttribute("userId", principal.getUser().);
        System.out.println("hitting prof");
        model.addAttribute("allGroups", groupService.getAllGroups());
        Student s = Userservice.findByName(principal.getName());
        System.out.println(groupService.getGroupsStudentBelongsToById(s.getId()));
        model.addAttribute("myGroups", groupService.getGroupsStudentBelongsToById(s.getId()));
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
