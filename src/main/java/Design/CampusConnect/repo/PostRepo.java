package Design.CampusConnect.repo;

import Design.CampusConnect.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Integer> {
}
