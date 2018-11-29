package Design.CampusConnect.repo;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepo extends CrudRepository<Group, Integer> {

    Group findById(int id);
    Group findByName(String name);
    Iterable<Group> findAll();

//    Iterable<Student> findStudentsInGroupById(int groupId){
//
//    }

}
