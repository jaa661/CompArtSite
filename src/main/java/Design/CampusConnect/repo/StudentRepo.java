package Design.CampusConnect.repo;

import Design.CampusConnect.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {

    Student findByUsername(String username);
    Student findByEmail(String email);
    Student findById(int id);

}