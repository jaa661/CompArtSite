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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("groupName", GroupService.GetNameById(groupId));
        model.addAttribute("groupId", groupId);
        return "group-page";
    }

    @RequestMapping(value = "/group/users/{groupId}") ///{groupId}")
    public String getUsers(Principal principal, Model model, @PathVariable int groupId) {
//        model.addAttribute("user", UserService.findByName(principal.getName()));
//        model.addAttribute("feed", PostService.getPostsByGroupId(groupId));
        model.addAttribute("users", GroupService.getStudentsInGroupById(groupId));
//        model.addAttribute("groupName", GroupService.GetNameById(groupId));
//        model.addAttribute("groupId", groupId);
        System.out.println("users");
        return "members-in-group";
    }

    @RequestMapping(value = "/user/groups")
    public String manageGroups(Principal principal, Model model) {
        model.addAttribute("user", UserService.findByName(principal.getName()));
        model.addAttribute("username", principal.getName());
        Student student = UserService.findByName(principal.getName());
        System.out.println(GroupService.getGroupsStudentBelongsToById(student.getId()));
//        model.addAttribute("userId", student.getId());
        System.out.println(student.getId());

        model.addAttribute("allGroups", GroupService.getAllGroupsUserIsNotIn(student.getId()));
        model.addAttribute("myGroups", GroupService.getGroupsStudentBelongsToById(student.getId()));
        System.out.println("hitting manage groups");
        return "manage-groups";
    }

    @RequestMapping(value = "/group/add")
    String newGroup(int creatorId, String groupName, Model model, Principal principal) {
        GroupService.studentCreateGroup(creatorId, groupName);
        return "redirect:/user/groups"; //manageGroups(principal, model);
    }

    @RequestMapping(value = "/group/join", method = RequestMethod.POST) //  method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String joinGroup(int newMemberId, int groupId, RedirectAttributes redirectAttributes) {
        GroupService.studentJoinGroup(newMemberId, groupId);
        redirectAttributes.addAttribute("groupId", groupId);
        return "redirect:/group/list/{groupId}";
    }

}
