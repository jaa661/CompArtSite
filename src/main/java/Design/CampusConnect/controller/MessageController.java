package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.MessageService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class MessageController {

    @Autowired
    MessageService service;

    @Autowired
    UserService Userservice;

    @Autowired
    GroupService Groupservice;

    @Autowired
    PostService postservice;


    @GetMapping("/messages")
    public String messages(Principal principal, Model model) {
        Student user = Userservice.findByName(principal.getName());
        model.addAttribute("user", user);
        //model.addAttribute("group", Groupservice.findById());
        model.addAttribute("messages", service.getMessagesSentBy(user.getId()));
        System.out.println("hitting messages");
        return "messages";
    }

    @GetMapping("/messages/{userId}")
    public String messagesWith(Principal principal, Model model, @PathVariable int userId) {
        Student user = Userservice.findByName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("friend", Userservice.findById(userId));
        model.addAttribute("messages", service.getMessagesWith(user.getId(), userId));
        System.out.println("hitting messages");
        return "messages";
    }

    @RequestMapping(value = "/message/add")
    String addpost(String content, String sender, String sendee, Model model, Principal principal) {
        service.sendMessage(content, sender, sendee);
        int sendeeId = Userservice.findByName(sendee).getId();
        return messagesWith(principal, model, sendeeId);
    }

}
