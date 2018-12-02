package Design.CampusConnect.controller;

import Design.CampusConnect.Email.email;
import Design.CampusConnect.Error.UserAlreadyExistException;
import Design.CampusConnect.Pojos.UserDto;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
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
        // Why would register already have a set user? Or is this just initializing the user struct? - Alex
        modelAndView.addObject("user", user);
        System.out.println("user: " + user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @Autowired
    @Qualifier("CampusConnectMailSender")
    public email mailSender;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(

            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors, Model model) {

        System.out.println("hitting Post Register");

        Student registered = new Student();
        System.out.println("new student");
        if (!result.hasErrors()) {
            System.out.println("attempting to create user account with no errors");
            registered = createUserAccount(accountDto, result);


            String from = "confirmcampusconnect@gmail.com";
            String to = accountDto.getEmail();
            String subject = "Confirm Account!";
            String body = "Click Here: http://campusconnect.ddns.net/confirm/" + accountDto.getUserName();

            mailSender.sendMail(from, to, subject, body);
            ModelAndView x = new ModelAndView("login", "user", accountDto);
            x.addObject("error", "Check your email and confirm to login!");
            return x;
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", accountDto);
        }
        else {
            return new ModelAndView("login", "user", accountDto);
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
