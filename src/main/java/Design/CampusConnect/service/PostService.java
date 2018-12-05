package Design.CampusConnect.service;

import Design.CampusConnect.entity.Post;
import Design.CampusConnect.repo.GroupRepo;
import Design.CampusConnect.repo.PostRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private StudentRepo studentRepository;
    @Autowired
    private GroupRepo groupRepository;

    /*public Post makePost(final Post newPost) {
        Post post = new Post();

        post.setContent(newPost.getContent());
        post.setPostedBy(newPost.getPostedBy());
        post.setPostedIn(newPost.getPostedIn());

        System.out.println(post);
        return postRepository.save(post);
    }*/
    public Post makePost(String content, Blob b, boolean hasImage, int posterId, int groupId) {
        Post post = new Post();

        post.setPostedByUser(studentRepository.findById(posterId));

        post.setPostedInGroup(groupRepository.findById(groupId));
        post.setContent(content);

        post.setImg(b);
        post.setHasImage(hasImage);
        post.setPostedBy(posterId);
        post.setPostedIn(groupId);

        System.out.println(post);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(){

        System.out.println("Fetching all posts");
        return postRepository.findAll();

    }

    public List<Post> getPostsByGroupId(int groupId){

        System.out.println("Fetching all posts in a given groupId");
        return  postRepository.findByPostedIn(groupId);
    }

    public List<Post> getPostsByStudentId(int studentId){

        System.out.println("Fetching all posts in a given studentId");
        return postRepository.findByPostedBy(studentId);
    }

//    public Iterable<Post> getPostsByStudentIdAt(int studentId, int index){
//
//        System.out.println("Fetching all posts in a given studentId");
//        return postRepository.findByPostedByAt(studentId, index);
//    }

//    public Iterable<Post> getPostsByStudentIdAt(int studentId, Pageable page){
//
//        System.out.println("Fetching all posts in a given studentId but pages");
//        //System.out.println(postRepository.findByPostedByAt(studentId, page));
//        System.out.println("success");
//        Slice<Post> slicePost = postRepository.findByPostedByAt2(studentId, page);
//        List<Post> listPost = slicePost.getContent();
//        return postRepository.findByPostedByAt(studentId, page);
//    }

    public Post getPostById(int postid){

        System.out.println("Fetching all posts in a given studentId");
        return postRepository.findPostById(postid);
    }
}
