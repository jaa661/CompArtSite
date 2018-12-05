package Design.CampusConnect.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@NamedQuery(name = "Post.findByPostedIn", query = "SELECT p FROM Post p WHERE p.postedIn = ?1")
@Table(name = "post", schema = "campus_connect")
public class Post implements Comparable<Post>{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer postedBy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student postedByUser;

    private Integer postedIn;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group postedInGroup;

    @Column(columnDefinition="TEXT")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public Integer getPostedIn() {
        return postedIn;
    }

    public void setPostedIn(Integer postedIn) {
        this.postedIn = postedIn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Student getPostedByUser() {
        return postedByUser;
    }

    public void setPostedByUser(Student postedByUser) {
        this.postedByUser = postedByUser;
    }

    public Group getPostedInGroup() {
        return postedInGroup;
    }

    public void setPostedInGroup(Group postedInGroup) {
        this.postedInGroup = postedInGroup;
    }
    @Override
    public int compareTo(Post other) {
        if(other.getId()<=this.id) {
            return -1;
        }
        else{
            return 1;
        }
    }
}
