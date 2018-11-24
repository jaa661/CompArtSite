package Design.CampusConnect.controller;

import Design.CampusConnect.Error.UserAlreadyExistException;
import Design.CampusConnect.Pojos.UserDto;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.UserService;
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
public class RegisterViewController {

    @Autowired
    UserService service;

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
            return new ModelAndView("profile", "user", accountDto);
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
}
