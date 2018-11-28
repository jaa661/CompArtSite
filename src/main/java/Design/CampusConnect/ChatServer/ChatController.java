package Design.CampusConnect.ChatServer;

import Design.CampusConnect.entity.Student;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Date;

@Controller
public class ChatController 
{
    @MessageMapping("/chat/{roomId}")
    @SendTo("/roomId/{roomId}")
    public Message handleMessage(@DestinationVariable("roomId") String roomid , String from, Message message) throws Exception
    {
        System.out.println("Message received for room: " + roomid);

       // message.setChatRoomId(Integer.parseInt(roomid));
       // message.getFrom() =

	    return new Message(Integer.parseInt(roomid), message.getFrom(), message.getMessage());
    }
}
