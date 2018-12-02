package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Student;
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
public class StudentController {

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    PostService postService;

    @RequestMapping("/user/{studentId}")
    public String profile(Principal principal, Model model, @PathVariable int studentId) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("username", principal.getName());
        System.out.println("hitting user "+studentId);
        model.addAttribute("visitedStudentUsername", userService.findById(studentId).getUsername());

        model.addAttribute("myGroups", groupService.getGroupsStudentBelongsToById(studentId));
        model.addAttribute("myPosts", postService.getPostsByStudentId(studentId));
        return "student-profile-view";
    }

}