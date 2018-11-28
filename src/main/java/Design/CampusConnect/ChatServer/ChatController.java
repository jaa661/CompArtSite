package Design.CampusConnect.ChatServer;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/*
 * Chat Controller listens for chat topic and responds with a message.
 *
 * @Author Jay Sridhar
 */
@Controller
public class ChatController 
{
    @MessageMapping("/chat/{roomId}")
    @SendTo("/roomId/messages")
    public OutputMessage handleMessage(@DestinationVariable("roomId") String roomId, OutputMessage message) throws Exception
    {
        System.out.println("Message received for room: " + roomId);

	    return new OutputMessage(message.getFrom(), message.getMessage(), roomId);
    }
}
