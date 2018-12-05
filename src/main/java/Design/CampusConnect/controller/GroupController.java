package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

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
        Student user = UserService.findByName(principal.getName());
        int userId = user.getId();
        System.out.println("user is" +  userId);
        model.addAttribute("user", UserService.findByName(principal.getName()));
        List<Post> allPosts = PostService.getPostsByGroupId(groupId);
        int offset = 0;
        int limit = offset+100;
        if(allPosts.size()<100){
            offset = 0;
            limit = offset+allPosts.size();
        }else{
            offset = 0;
            limit = offset+100;
        }
        List<Post> myPosts = allPosts.subList(offset, limit);
        model.addAttribute("feed", myPosts);
        model.addAttribute("groupName", GroupService.GetNameById(groupId));
        model.addAttribute("groupId", groupId);
//        model.addAttribute("inGrp", GroupService.checkGroupStudentIn( userId,groupId));
        Boolean inGrp = GroupService.checkGroupStudentIn( userId,groupId);
        if (inGrp == Boolean.TRUE){
            return "group-page-if-joined";}
        else return "group-page-if-not-joined";
    }

    @PostMapping("/user/group/search/")
    public String search(String groupName){
        return "redirect:/group/list/" + GroupService.findGroupByName(groupName).getId();
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
    String newGroup(Principal principal, int creatorId, String groupName, Model model) {

        if (GroupService.exists(groupName)){
            model.addAttribute("error", "Group already exists :(");
            return manageGroups(principal, model);
        }
        else{
            GroupService.studentCreateGroup(creatorId, groupName);
            return "redirect:/user/groups";
        }
    }


    @RequestMapping(value = "/group/join", method = RequestMethod.POST) //  method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String joinGroup(int newMemberId, int groupId, RedirectAttributes redirectAttributes, Model model) {
        boolean check = GroupService.studentJoinGroup(newMemberId, groupId);
        if(!check) {
            model.addAttribute("error", "Could not join group");
            return "redirect:/user/groups";
        }
        redirectAttributes.addAttribute("groupId", groupId);
        return "redirect:/group/list/{groupId}";
    }

    @RequestMapping(value = "/group/leave", method = RequestMethod.POST) //  method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String  leaveGroup(int newMemberId, int groupId, RedirectAttributes redirectAttributes) {
        GroupService.studentLeaveGroup(newMemberId, groupId);
//        redirectAttributes.addAttribute("groupId", groupId);
        return "redirect:/user/groups";
    }

//    @RequestMapping(value = "/group/leave", method = RequestMethod.POST)//,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    void leaveGroup(int newMemberId, int groupId, RedirectAttributes redirectAttributes) {
//        GroupService.studentLeaveGroup(newMemberId, groupId);
//    }

}
