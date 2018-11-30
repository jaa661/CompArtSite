package Design.CampusConnect.controller;

import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class StudentController {

    @Autowired
    UserService service;

    @RequestMapping("/user/{studentId}")
    public String profile(Principal principal, Model model, @PathVariable int studentId) {
        model.addAttribute("user", service.findById(studentId));
        model.addAttribute("username", service.findById(studentId).getUsername());
        System.out.println("hitting user "+studentId);
        return "Profile";
    }

//    @RequestMapping("/user/groups")
//    public String mygroups(Principal principal, Model model, @PathVariable int studentId) {
//        model.addAttribute("user", service.findById(studentId));
//        model.addAttribute("username", service.findById(studentId).getUsername());
//        System.out.println("hitting user "+studentId);
//        return "Profile";
//    }
}