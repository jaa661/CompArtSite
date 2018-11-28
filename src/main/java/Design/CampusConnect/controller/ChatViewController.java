package Design.CampusConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ChatViewController {
    @GetMapping("/ws")
    public String showchat(Principal pricipal, Model model) {
        model.addAttribute("username", pricipal.getName());
        System.out.println("hitting chat");
        return "chatroom";
    }
}

//http://hmkcode.com/first-time-java-api-for-websocket/