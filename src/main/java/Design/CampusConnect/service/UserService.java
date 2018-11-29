package Design.CampusConnect.service;


import Design.CampusConnect.entity.Student;
import Design.CampusConnect.Pojos.UserDto;
import Design.CampusConnect.repo.RoleRepo;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Service
public class UserService implements IUserService {
    @Autowired
    private StudentRepo repository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
////            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
////        }
          //Student user = new Student(accountDto.getFirstName(),accountDto.getLastName(),accountDto.getPassword(),accountDto.getEmail());
        Student user = new Student();
//        System.out.println(accountDto);
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setUsername(accountDto.getUserName());
//        ArrayList<Integer> groups = new ArrayList();
        Set<Integer> groups = new HashSet<>();
        groups.add(0);
        user.setGroups(groups);
        System.out.println(accountDto.getPassword());
        user.setPassword(passwordEncoder().encode(accountDto.getPassword()));
        System.out.println(passwordEncoder().encode(accountDto.getPassword()));

        user.setEmail(accountDto.getEmail());
//        //user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));

        System.out.println(user);
        repository.save(user);
        return user;
    }
    public Student findByName(String name) {
        return repository.findByUsername(name);
    }
    public Student findById(int Id) {
        return repository.findById(Id);
    }

}