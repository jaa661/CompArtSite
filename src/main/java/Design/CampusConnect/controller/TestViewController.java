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
    @Autowired UserService service;

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

    @RequestMapping("/login")
    public String login() {
        System.out.println("hitting login");
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, Student user){
        System.out.println("hitting GET Register");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(

            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {
        System.out.println("hitting Post Register");

        Student registered = new Student();
        System.out.println("new student");
        if (!result.hasErrors()) {
            System.out.println("attempting to create user account with no errors");
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", accountDto);
        }
        else {
            return new ModelAndView("register", "user", accountDto);
        }
    }
    private Student createUserAccount(UserDto accountDto, BindingResult result) {
        Student registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return registered;
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "home";
    }
}