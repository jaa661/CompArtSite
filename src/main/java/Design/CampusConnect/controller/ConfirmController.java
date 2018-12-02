package Design.CampusConnect.controller;

import Design.CampusConnect.ChatServer.Message;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConfirmController {

    @Autowired
    UserService service;

    @GetMapping("/confirm/{accountID}")
    @ResponseBody
    public String handleMessage(@PathVariable("accountID") String accountID) throws Exception
    {
        service.confirmAccount(accountID);
        System.out.println("Message received for account: " + accountID);

        return "Activated!";
        // message.setChatRoomId(Integer.parseInt(roomid));
        // message.getFrom() =

        //return;
    }
}
