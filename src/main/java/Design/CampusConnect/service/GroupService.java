package Design.CampusConnect.service;

import Design.CampusConnect.entity.Group;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.repo.GroupRepo;
import Design.CampusConnect.repo.PostRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GroupService {


    @Autowired
    private GroupRepo repository;

    @Autowired
    private StudentRepo studentrepository;


    public Group findById(int Id) {
        return repository.findById(Id);
    }

    public String GetNameById(int Id) {
        return repository.findById(Id).getName();
    }

    public Group createGroup(final Group newGroup) {
        Group group = new Group();
        System.out.println("creating new group with no original User");

        group.setName(newGroup.getName());

        System.out.println(group);
        return repository.save(group);
    }

    public Group createGroup(final Group newGroup, final Student creator) {
        Group group = new Group();
        System.out.println("creating new group created by: " + creator.getUsername());

        group.setName(newGroup.getName());
        creator.getGroups().add(newGroup.getId());
        creator.setGroups(creator.getGroups());

        System.out.println(group);
        return repository.save(group);
    }

    public boolean studentCreateGroup(int creatorId, String groupName) {
        Group group = new Group();
        Student toUpdate = studentrepository.findById(creatorId);
        if(toUpdate == null){
            return false;
        }
        System.out.println(toUpdate);
        System.out.println("creating new group created by: " + toUpdate.getUsername());

        group.setName(groupName);
        Set<Integer> studentsInGroup = new HashSet<>();
        studentsInGroup.add(creatorId);
        group.setStudentsInGroup(studentsInGroup);
        repository.save(group);
        toUpdate.getGroups().add(group.getId());
        studentrepository.save(toUpdate);
        System.out.println(studentrepository.findById(toUpdate.getId()));
        System.out.println(group);
        return true;
    }

    public boolean studentJoinGroup(int newMemberId, int groupId) {

        Student studentToUpdate = studentrepository.findById(newMemberId);
        Group groupToUpdate = repository.findById(groupId);

        if(studentToUpdate == null || groupToUpdate == null){
            return false;
        }

        System.out.println(studentToUpdate);
        System.out.println("user: " + studentToUpdate.getUsername()
                + " is attempting to join group " + groupToUpdate.getName());

        studentToUpdate.getGroups().add(groupId);
        studentrepository.save(studentToUpdate);

        Set<Integer> groups = groupToUpdate.getStudentsInGroup();
        if(groups == null){
            groups = new HashSet<>();
            groups.add(newMemberId);
        }else{
            groups.add(newMemberId);
        }
        System.out.println(groups);
        groupToUpdate.setStudentsInGroup(groups);
        repository.save(groupToUpdate);

        System.out.println("user: " + studentToUpdate.getUsername() + " joined: " + groupToUpdate.getName());
        return true;
    }




    public void studentLeaveGroup(int newMemberId, int groupId) {
        Student studentToUpdate = studentrepository.findById(newMemberId);
        Group groupToUpdate = repository.findById(groupId);

        System.out.println(studentToUpdate);
        System.out.println("user: " + studentToUpdate.getUsername()
                + " is attempting to leave group " + groupToUpdate.getName());

        studentToUpdate.getGroups().remove(groupId);
        studentrepository.save(studentToUpdate);

        groupToUpdate.getStudentsInGroup().remove(newMemberId);
//        if(groups == null){
//            groups = new HashSet<>();
//            groups.r(newMemberId);
//        }else{
//            groups.add(newMemberId);
//        }
        System.out.println(groupToUpdate);
//        groupToUpdate.setStudentsInGroup(groups);
        repository.save(groupToUpdate);

        System.out.println("user: " + studentToUpdate.getUsername() + " left: " + groupToUpdate.getName());
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

    public Iterable<Student> getStudentsInGroupById(int groupId){
        Group group = repository.findById(groupId);
        System.out.println("Getting members of: " + group.getName());
        Iterable<Integer> studentListIds = group.getStudentsInGroup();
        ArrayList<Student> students = new ArrayList<>();
        for(Integer studentId:studentListIds){
            Student student = studentrepository.findById(studentId.intValue());
            students.add(student);
        }

        System.out.println("Members are: " + studentListIds);

        return students;
    }

    public Iterable<Group> getAllGroupsUserIsNotIn(int studentId){
        Iterable<Group> allGroups = repository.findAll();
        Iterable<Group> studentGroups = getGroupsStudentBelongsToById(studentId);
        Set<Group> studentGroupsSet = new HashSet<>();
        for (Group g: studentGroups){
            studentGroupsSet.add(g);
        }
        ArrayList<Group> groupsStudentNotIn = new ArrayList<>();
        for (Group g: allGroups){
            if(!studentGroupsSet.contains(g)){
                groupsStudentNotIn.add(g);
            }
        }
        return groupsStudentNotIn;
    }

    public Iterable<Group> getAllGroups(){

        return repository.findAll();
    }

    public Iterable<Group> getGroupsStudentBelongsToById(int studentId){
        Student student = studentrepository.findById(studentId);
        Iterable<Integer> studentGroupsIds = student.getGroups();
        ArrayList<Group> groups = new ArrayList<>();
        for(Integer groupId:studentGroupsIds){
            Group group = repository.findById(groupId.intValue());
            if (group != null){
                groups.add(group);
            }

        }
        System.out.println("Groups student has are: " + studentGroupsIds);
        return groups;
    }

    public Boolean checkGroupStudentIn(int studentId, int grpId){
        Boolean inGroup = Boolean.FALSE;
        Student student = studentrepository.findById(studentId);
        Iterable<Integer> studentGroupsIds = student.getGroups();
        ArrayList<Group> groups = new ArrayList<>();
        for(Integer groupId:studentGroupsIds){
            if (groupId == grpId) {inGroup = Boolean.TRUE;}
        }
        System.out.println("Groups student has are: " + studentGroupsIds);
        return inGroup;
    }


//     public void getStudentsInGroupByName(String groupName);



//    public void studentPostToGroupByID(int studentId, int groupId, String textPost);

}
