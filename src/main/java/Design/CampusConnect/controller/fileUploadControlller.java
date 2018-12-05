package Design.CampusConnect.controller;

import Design.CampusConnect.entity.Post;
import Design.CampusConnect.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Controller
public class fileUploadControlller {
    @Autowired // This means to get the bean called userRepository
    private Design.CampusConnect.service.PostService PostService;

    @RequestMapping(value = "/getImageFromPost/{postId}", method = RequestMethod.GET)
    public HttpEntity<byte[]> displayPhoto(@PathVariable("postId") int postId) {
        Post z = PostService.getPostById(postId);
        Blob imageT = z.getImg();
        byte[] image = null;
        int blen = 0;
        try {
            blen = (int) imageT.length();
            image = imageT.getBytes(1, blen);
        }catch (Exception e){
            System.out.println("SQL ERROR LOADING IMAGE");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(blen);
        return new HttpEntity<byte[]>(image, headers);
    }



}
