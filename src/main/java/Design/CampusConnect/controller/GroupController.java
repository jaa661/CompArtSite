package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class GroupController {

    @Autowired
    PostService PostService;

    @Autowired
    UserService UserService;

    @Autowired
    GroupService GroupService;

    @RequestMapping(value = "/group/list/{groupId}")
    public String messages(Principal principal, Model model, @PathVariable int groupId) {
        model.addAttribute("user", UserService.findByName(principal.getName()));
        model.addAttribute("feed", PostService.getPostsByGroupId(groupId));
        return "mainfeed";
    }

    @RequestMapping("/user/groups")
    public String manageGroups(Principal principal, Model model) {
        model.addAttribute("user", UserService.findByName(principal.getName()));
        model.addAttribute("username", principal.getName());
        Student student = UserService.findByName(principal.getName());
        System.out.println(GroupService.getGroupsStudentBelongsToById(student.getId()));
        model.addAttribute("userId", student.getId());
        System.out.println(student.getId());

        model.addAttribute("allGroups", GroupService.getAllGroups());

        model.addAttribute("myGroups", GroupService.getGroupsStudentBelongsToById(student.getId()));
        System.out.println("hitting prof");
        return "ManageGroups";
    }

    @RequestMapping(value = "/group/add") //method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String newGroup(int creatorId, String groupName, Model model, Principal principal) {
        GroupService.studentCreateGroup(creatorId, groupName);
        return manageGroups(principal, model);
    }


}
