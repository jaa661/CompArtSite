package Design.CampusConnect.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;


    @Column(name = "students_in_group")
    @ElementCollection(targetClass=Integer.class)
    private Set<Integer> studentsInGroup = new HashSet<>();

//    private ArrayList<Integer> studentsInGroup = new ArrayList<>();

    // Getters
    public Integer getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public Set<Integer> getStudentsInGroup() {
        return studentsInGroup;
    }
//    public ArrayList<Integer> getStudentsInGroup() {
//        return studentsInGroup;
//    }
//
//    // Setters
//    public void setStudentsInGroup(ArrayList<Integer> studentsInGroup) {
//        this.studentsInGroup = studentsInGroup;
//    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setStudentsInGroup(Set<Integer> studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
    }
}
