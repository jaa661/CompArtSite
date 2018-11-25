package Design.CampusConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatViewController {
    @GetMapping("/ws")
    String get(){
        return "chatroom";
    }
}

//http://hmkcode.com/first-time-java-api-for-websocket/