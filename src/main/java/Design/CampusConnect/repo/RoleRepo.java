package Design.CampusConnect.repo;

import Design.CampusConnect.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
