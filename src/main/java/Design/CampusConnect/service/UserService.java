package Design.CampusConnect.service;

import Design.CampusConnect.Error.UserAlreadyExistException;
import Design.CampusConnect.entity.Student;
import Design.CampusConnect.entity.UserDto;
import Design.CampusConnect.repo.RoleRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserService implements IUserService {
    @Autowired
    private StudentRepo repository;

    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Autowired
    private RoleRepo roleRepository;

    private boolean emailExist(String email) {
        if (!(email != null && !email.isEmpty())) {
            System.out.println("issue with email sent");
            email = "";
        }
        boolean check = (repository.findByEmail(email) != null);
        return check;
    }

    @Override
    public Student registerNewUserAccount(final UserDto accountDto) {
//        if (emailExist(accountDto.getEmail())) {
////            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
////        }
          //Student user = new Student(accountDto.getFirstName(),accountDto.getLastName(),accountDto.getPassword(),accountDto.getEmail());
        Student user = new Student();
//        System.out.println(accountDto);
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setUsername(accountDto.getUserName());
        user.setPassword(passwordEncoder().encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
//        //user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));

        System.out.println(user);
        repository.save(user);
        return user;
    }
}