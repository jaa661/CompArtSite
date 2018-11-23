package Design.CampusConnect.service;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.repo.GroupRepo;
import Design.CampusConnect.repo.PostRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupService {


    @Autowired
    private GroupRepo repository;

    @Autowired
    private StudentRepo studentrepository;

    public Group createGroup(final Group newGroup) {
        Group group = new Group();
        System.out.println("creating new group with no original User");

        group.setName(newGroup.getName());

        System.out.println(group);
        return repository.save(group);
    }

    public Group createGroup(final Student creator, final Group newGroup) {
        Group group = new Group();
        System.out.println("creating new group created by: " + creator.getUsername());

        group.setName(newGroup.getName());
        creator.getGroups().add(newGroup.getId());
        creator.setGroups(creator.getGroups());

        System.out.println(group);
        return repository.save(group);
    }

    public Group studentCreateGroup(int creatorId, String groupName) {
        Group group = new Group();
        Student toUpdate = studentrepository.findById(creatorId);
        System.out.println(toUpdate);
        System.out.println("creating new group created by: " + toUpdate.getUsername());

        group.setName(groupName);
        repository.save(group);
        toUpdate.getGroups().add(group.getId());
        studentrepository.save(toUpdate);
        System.out.println(studentrepository.findById(toUpdate.getId()));
        System.out.println(group);
        return group;
    }

    public void studentJoinGroup(int newMemeberId, int groupId) {
        Student toUpdate = studentrepository.findById(newMemeberId);
        System.out.println(toUpdate);
        System.out.println("user: " + toUpdate.getUsername() + " is attempting to join group");
        toUpdate.getGroups().add(groupId);
        studentrepository.save(toUpdate);
        Group myNewGroup = repository.findById(groupId);
        System.out.println("user: " + toUpdate.getUsername() + " joined: " + myNewGroup.getName());
    }

    public void studentJoinGroup(int newMemeberId, String groupName) {
        Group myNewGroup = repository.findByName(groupName);

        Student toUpdate = studentrepository.findById(newMemeberId);
        System.out.println(toUpdate);
        System.out.println("user: " + toUpdate.getUsername() + " is attempting to join group");
        toUpdate.getGroups().add(myNewGroup.getId());
        studentrepository.save(toUpdate);
        System.out.println("user: " + toUpdate.getUsername() + " joined: " + myNewGroup.getName());
    }

}
