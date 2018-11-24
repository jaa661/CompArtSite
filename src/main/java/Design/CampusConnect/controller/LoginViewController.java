package Design.CampusConnect.controller;

import Design.CampusConnect.Error.UserAlreadyExistException;
import Design.CampusConnect.Pojos.UserDto;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.UserService;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginViewController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String get() {
        System.out.println("hitting GET login");
        return "login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(){
        System.out.println("hitting POST login");
        Boolean success = false;

        if (success)
            return "home";
        else
            return "login";
    }




}
