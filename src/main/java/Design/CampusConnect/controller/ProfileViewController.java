package Design.CampusConnect.controller;

import Design.CampusConnect.OffsetPageable;
import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileViewController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @RequestMapping("/Profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("username", principal.getName());
        Student student = userService.findByName(principal.getName());
        System.out.println(groupService.getGroupsStudentBelongsToById(student.getId()));
        model.addAttribute("myGroups", groupService.getGroupsStudentBelongsToById(student.getId()));
//        model.addAttribute("myPosts", postService.getPostsByStudentId(student.getId()));

        List<Post> allPosts = postService.getPostsByStudentId(student.getId());
        int offset = 0;
        int limit = 0;
        if(allPosts.size()<100){
            offset = 0;
            limit = offset+allPosts.size();
//            limit = offset+2;
        }else{
            offset = 0;
            limit = offset+100;
        }
        List<Post> myPosts = allPosts.subList(offset, limit);        System.out.println("posts on profile");
        System.out.println(allPosts);
        System.out.println(myPosts);
        model.addAttribute("myPosts", myPosts);

        System.out.println("hitting prof");
        return "profile";
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
