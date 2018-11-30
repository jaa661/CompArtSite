package Design.CampusConnect.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student sentBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student sentTo;


    private int sentBId;

    private int sentTId;
    private String content;

    private Date postTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getSentBy() {
        return sentBy;
    }

    public void setSentBy(Student sentBy) {
        this.sentBy = sentBy;
    }

    public Student getSentTo() {
        return sentTo;
    }

    public void setSentTo(Student sentTo) {
        this.sentTo = sentTo;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public int getSentBId() {
        return sentBId;
    }

    public void setSentBId(int sentById) {
        this.sentBId = sentById;
    }

    public int getSentTId() {
        return sentTId;
    }

    public void setSentTId(int sentToId) {
        this.sentTId = sentToId;
    }

    @Override
    public int compareTo(Message other) {
        if(other.getId()<=this.id) {
            return 1;
        }
        else{
            return -1;
        }
    }
}
