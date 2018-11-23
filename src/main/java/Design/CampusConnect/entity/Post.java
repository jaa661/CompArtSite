package Design.CampusConnect.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;

@Entity
//@NamedQuery(name = "Post.findByPostedIn", query = "SELECT p FROM Post p WHERE p.postedIn = ?1")
@Table(name = "post", schema = "campus_connect")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer postedBy;

    private Integer postedIn;

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
}
