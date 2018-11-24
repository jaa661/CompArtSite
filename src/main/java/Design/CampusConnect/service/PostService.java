package Design.CampusConnect.service;

import Design.CampusConnect.entity.Post;
import Design.CampusConnect.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepository;

    public Post makePost(final Post newPost) {
        Post post = new Post();

        post.setContent(newPost.getContent());
        post.setPostedBy(newPost.getPostedBy());
        post.setPostedIn(newPost.getPostedIn());

        System.out.println(post);
        return postRepository.save(post);
    }
    public Post makePost(String content, int posterId, int groupId) {
        Post post = new Post();

        post.setContent(content);
        post.setPostedBy(posterId);
        post.setPostedIn(groupId);

        System.out.println(post);
        return postRepository.save(post);
    }

    public Iterable<Post> getAllPosts(){

        System.out.println("Fetching all posts");
        return postRepository.findAll();

    }

    public Iterable<Post> getPostsByGroupId(int groupId){

        System.out.println("Fetching all posts in a given groupId");
        return  postRepository.findByPostedIn(groupId);
    }

    public Iterable<Post> getPostsByStudentId(int studentId){

        System.out.println("Fetching all posts in a given studentId");
        return postRepository.findByPostedBy(studentId);
    }
}
