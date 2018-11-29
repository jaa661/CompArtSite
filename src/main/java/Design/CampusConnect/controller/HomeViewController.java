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

@Controller
public class HomeViewController {
    @RequestMapping("/home")
    public String home() {
        System.out.println("hitting home");
        return "home";
    }
    @RequestMapping("/")
    public String index() {
        System.out.println("hitting index");
        return "home";
    }
}
