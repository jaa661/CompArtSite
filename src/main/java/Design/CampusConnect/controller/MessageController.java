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
import java.util.Comparator;
import java.util.Map;


@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    PostService postService;

    public Comparator<Map<String, String>> mapComparator = new Comparator<Map<String, String>>() {
        public int compare(Map<String, String> m1, Map<String, String> m2) {
            return m1.get("id").compareTo(m2.get("id"));
        }
    };

    @GetMapping("/messages")
    public String messages(Principal principal, Model model) {
        Student user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        //model.addAttribute("group", Groupservice.findById());
        model.addAttribute("messages", messageService.getMessagesRecent(user.getId()));
        System.out.println("hitting messages");
        return "messages";
    }

    @GetMapping("/messages/{userId}")
    public String messagesWith(Principal principal, Model model, @PathVariable int userId) {
        Student user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("friend", userService.findById(userId));
        model.addAttribute("messages", messageService.getMessagesWith(user.getId(), userId));
        model.addAttribute("mapComparator", mapComparator);

        System.out.println("hitting messages");
        return "messagesID";
    }

    @RequestMapping(value = "/message/add")
    String addpost(String content, String sender, String sendee, Model model, Principal principal) {
        messageService.sendMessage(content, sender, sendee);
        int sendeeId = userService.findByName(sendee).getId();
        return messagesWith(principal, model, sendeeId);
    }



}
