package Design.CampusConnect.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private ArrayList<Integer> studentsInGroup = new ArrayList<>();

    // Getters
    public Integer getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public ArrayList<Integer> getStudentsInGroup() {
        return studentsInGroup;
    }

    // Setters
    public void setStudentsInGroup(ArrayList<Integer> studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }
}
