package Design.CampusConnect.service;

import Design.CampusConnect.entity.Message;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.repo.GroupRepo;
import Design.CampusConnect.repo.MessageRepo;
import Design.CampusConnect.repo.PostRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepository;

    @Autowired
    private UserService studentService;

    @Autowired
    private GroupRepo groupRepository;

    public Message sendMessage(String content, String sentBy, String sentTo) {
        Message msg = new Message();
        System.out.println("Sent by user named " + sentBy);
        Student sentByUser = studentService.findByName(sentBy);
        Student sentToUser = studentService.findByName(sentTo);
        msg.setContent(content);
        System.out.println("Sent by user object "+sentByUser);
        msg.setSentBy(sentByUser);
        msg.setSentTo(sentToUser);
        msg.setSentBId(sentByUser.getId());
        msg.setSentTId(sentToUser.getId());
        msg.setPostTime(new Date());
        return messageRepository.save(msg);
    }

    public Iterable<Message> getAllPosts(){

        System.out.println("Fetching all messages");
        return messageRepository.findAll();

    }

    public Iterable<Message> getMessagesSentBy(int myId){

        System.out.println("Fetching all messages sent by "+ myId);
        return  messageRepository.findByPostedBy(myId);
    }

    public Iterable<Message> getMessagesWith(int myId, int theirId){

        System.out.println("Fetching all messages sent by "+ myId+" and "+ theirId);
        return  messageRepository.findByPostedWith(myId,theirId);
    }

    public Iterable<Message> getMessagesRecent(int myId){

        System.out.println("Fetching all messages sent by/to "+ myId);
        return  messageRepository.findByRecent(myId);
    }
}
