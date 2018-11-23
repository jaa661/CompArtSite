package Design.CampusConnect.service;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.repo.GroupRepo;
import Design.CampusConnect.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {


    @Autowired
    private GroupRepo repository;

    public Group createGroup(final Group newGroup) {
        Group group = new Group();

        group.setId(newGroup.getId());
        group.setName(newGroup.getName());

        System.out.println(group);
        return repository.save(group);
    }

}
