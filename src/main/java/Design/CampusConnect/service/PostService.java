package Design.CampusConnect.service;

import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.entity.UserDto;
import Design.CampusConnect.repo.PostRepo;
import Design.CampusConnect.repo.RoleRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepo repository;

    public Post makePost(final Post newPost) {
        Post post = new Post();

        post.setContent(newPost.getContent());
        post.setPostedBy(newPost.getPostedBy());
        post.setPostedIn(newPost.getPostedIn());

        System.out.println(post);
        return repository.save(post);
    }
}
