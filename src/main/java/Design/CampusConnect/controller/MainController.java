package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Groups;
import Design.CampusConnect.entity.Post;
import Design.CampusConnect.entity.Students;
import Design.CampusConnect.repo.GroupRepo;
import Design.CampusConnect.repo.PostRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private StudentRepo studentRepository;

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private GroupRepo GroupRepository;

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private PostRepo PostRepository;


//    @GetMapping(path="/add") // Map ONLY GET Requests
//    public @ResponseBody
//    String addNewUser (@RequestParam String name
//            , @RequestParam String email) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Students n = new Students();
//        n.setName(name);
//        n.setEmail(email);
//        studentRepository.save(n);
//        return "Saved";
//    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Students newStudent(Students newStudent) {
        return studentRepository.save(newStudent);

    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Post newPost(Post newPost) {
        return PostRepository.save(newPost);

    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Students> getAllUsers() {
        // This returns a JSON or XML with the users
        return studentRepository.findAll();
    }

    @GetMapping(path="/find")
    public @ResponseBody
    Iterable<Students> getUser(@RequestParam Iterable<Integer> id) {
        // This returns a JSON with specific  user
        return studentRepository.findAllById(id);
    }
}

