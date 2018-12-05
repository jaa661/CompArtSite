package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Post;
import Design.CampusConnect.service.GroupService;
import Design.CampusConnect.service.PostService;
import Design.CampusConnect.service.UserService;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.util.List;

@Controller
public class FeedController {

    @Autowired
    PostService service;

    @Autowired
    UserService Userservice;

    @Autowired
    GroupService Groupservice;


    @RequestMapping(value = "/feed")
    public String sidebyside(Model model, Principal principal) {
        model.addAttribute("user", Userservice.findByName(principal.getName()));
        //model.addAttribute("group", Groupservice.findById());
        List<Post> allPosts = service.getAllPosts();
        int offset = 0;
        int limit = offset+100;
        if(allPosts.size()<100){
            offset = 0;
            limit = offset+allPosts.size();
//            limit = offset+2;
        }else{
            offset = 0;
            limit = offset+100;
        }
        List<Post> myPosts = allPosts.subList(offset, limit);
        model.addAttribute("feed", myPosts);
        return "chat-and-feed";
    }
    @RequestMapping(value = "/oldfeed")
    public String oldfeed(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("user", Userservice.findByName(principal.getName()));
        //model.addAttribute("group", Groupservice.findById());
        model.addAttribute("feed", service.getAllPosts());
        return "mainfeed";
    }
    @RequestMapping(value = "/post/add", method = RequestMethod.POST, consumes =  {"multipart/form-data", MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addpost(@RequestPart("file")
                           MultipartFile multipart, String content, int poster, int group, Model model, Principal principal) {
        System.out.println(content + poster );
        Blob blob = null;
        boolean hasImage = false;


        try {
            if (multipart.getBytes().length>0){
                hasImage = true;
            }
            blob = new javax.sql.rowset.serial.SerialBlob(multipart.getBytes()); // hibernate method for create blob
        } catch (Exception e){
            System.out.println("Error making blob!");
        }
        service.makePost(content, blob, hasImage, poster, group);
        return sidebyside(model, principal);
    }

    //@Autowired
    //private SessionFactory sessionFactory;

    @RequestMapping(value = "/group/post/add", method = RequestMethod.POST, consumes =  {"multipart/form-data", MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String addPostToGroup(@RequestPart("file")
            MultipartFile multipart, String content, int poster, int group, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        System.out.println(content + poster );
        Blob blob = null;
        boolean hasImage = false;


        try {
            if (multipart.getBytes().length>0){
                hasImage = true;
            }
            blob = new javax.sql.rowset.serial.SerialBlob(multipart.getBytes()); // hibernate method for create blob
        } catch (Exception e){
            System.out.println("Error making blob!");
        }
        service.makePost(content, blob, hasImage, poster, group);
        redirectAttributes.addAttribute("groupId",group );
        return "redirect:/group/list/{groupId}";
    }

}
