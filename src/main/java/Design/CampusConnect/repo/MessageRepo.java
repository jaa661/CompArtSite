package Design.CampusConnect.repo;


import Design.CampusConnect.entity.Message;
import Design.CampusConnect.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {

    @Query("SELECT p FROM Message p WHERE p.sentBId = :myId")
    List<Message> findByPostedBy(@Param("myId") int myId);

    @Query("SELECT p FROM Message p WHERE (p.sentBId = :myId AND p.sentTId = :theirId) OR (p.sentBId = :theirId AND p.sentTId = :myId)")
    List<Message> findByPostedWith(@Param("myId") int myId, @Param("theirId") int theirId);

    @Query("SELECT p FROM Message p WHERE (p.sentBId = :myId ) OR (p.sentTId = :myId)")
    List<Message> findByRecent(@Param("myId") int myId);

}
