package Design.CampusConnect.repo;

import java.util.List;
import Design.CampusConnect.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepo extends JpaRepository<Post, Integer> {


    // PostedIn is the groupId the Post is posted in
    @Query("SELECT p FROM Post p WHERE p.postedIn = :groupId")
    List<Post> findByPostedIn(@Param("groupId") int groupId);

    // PostedBy is the StudentId the Post is posted by
    @Query("SELECT p FROM Post p WHERE p.postedBy = :studentId")
    List<Post> findByPostedBy(@Param("studentId") int studentId);

    // PostedBy is the StudentId the Post is posted by
    @Query("SELECT p FROM Post p WHERE p.id = :postId")
    Post findPostById(@Param("postId") int postId);


    // PostedBy is the StudentId the Post is posted by
    @Query("SELECT p FROM Post p WHERE p.postedBy = :studentId")
    List<Post> findByPostedByAt(@Param("studentId") int studentId, Pageable page);

    @Query("SELECT p FROM Post p WHERE p.postedBy = :studentId")
    Slice<Post> findByPostedByAt2(@Param("studentId") int studentId, Pageable pageable);

}
