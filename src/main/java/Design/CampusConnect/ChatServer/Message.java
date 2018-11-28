package Design.CampusConnect.ChatServer;

import java.util.Date;

/*
 * Output message sent to client.
 *
 * @Author Jay Sridhar
 */
public class Message {

    //private int id;
    private String from;
    private String message;
    private Date time = new Date();
    private int chatRoomId;

    public Message() {
    }

    public Message(int chatroomId, String from, String message) {
        this.chatRoomId = chatroomId;
        this.from = from;
        this.message = message;
    }

   /*( public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

}
