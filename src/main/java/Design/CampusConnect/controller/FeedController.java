package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Post;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class FeedController {

    @Autowired
    PostService service;

    @Autowired
    UserService Userservice;

    @Autowired
    GroupService Groupservice;


    @RequestMapping(value = "/feed")
    public String sidebyside(Model model, Principal principal) {
        model.addAttribute("user", Userservice.findByName(principal.getName()));
        //model.addAttribute("group", Groupservice.findById());
        model.addAttribute("feed", service.getAllPosts());
        return "chatandfeed";
    }
    @RequestMapping(value = "/oldfeed")
    public String oldfeed(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("user", Userservice.findByName(principal.getName()));
        //model.addAttribute("group", Groupservice.findById());
        model.addAttribute("feed", service.getAllPosts());
        return "mainfeed";
    }
    @RequestMapping(value = "/post/add")
    String addpost(String content, int poster, int group, Model model, Principal principal) {
        System.out.println(content + poster );
        service.makePost(content, poster, group);

        return sidebyside(model, principal);
    }

    @RequestMapping(value = "/group/post/add")
    String addPostToGroup(String content, int poster, int group, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        System.out.println(content + poster );
        service.makePost(content, poster, group);
        redirectAttributes.addAttribute("groupId",group );
        return "redirect:/group/list/{groupId}";
    }

}
