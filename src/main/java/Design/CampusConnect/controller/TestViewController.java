package Design.CampusConnect.controller;


import Design.CampusConnect.Error.UserAlreadyExistException;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.Pojos.UserDto;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("")
public class TestViewController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("username", name);
        return "home";
    }

    @RequestMapping("/adduser")
    public String adduser(Model model) {
        return "AddUserForm";
    }

    @RequestMapping("/addpost")
    public String addpost(Model model) {
        return "PostMessageToGroup";
    }

    @RequestMapping("/home")
    public String home() {
        System.out.println("hitting home");
        return "home";
    }

    @RequestMapping("/Profile")
    public String profile(Principal pricipal, Model model) {
        model.addAttribute("username", pricipal.getName());
        System.out.println("hitting prof");
        return "Profile";
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "home";
    }
}