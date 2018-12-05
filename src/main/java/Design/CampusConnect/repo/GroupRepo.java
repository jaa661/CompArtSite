package Design.CampusConnect.repo;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepo extends CrudRepository<Group, Integer> {

    Group findById(int id);
    Group findByName(String name);
    Iterable<Group> findAll();

//    Iterable<Student> findStudentsInGroupById(int groupId){
//
//    }
// PostedBy is the StudentId the Post is posted by
    //@Query("SELECT p FROM groups p WHERE p.name = :name")
    //Post findPostById(@Param("name") String name);
}
