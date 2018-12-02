package Design.CampusConnect;

import Design.CampusConnect.entity.Student;
import Design.CampusConnect.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo StudentRepository;

    public MyUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("Searching for user: " + username);
        Student student = StudentRepository.findByUsername(username);
        System.out.println(student);
        if (student.isConfirmed()==false){
            throw new UsernameNotFoundException(username+": Email not confirmed yet!");
        }

        if (student == null) {
            throw new UsernameNotFoundException(username);
        }
        //return new MyUserPrincipal(student);
        String password = student.getPassword();
        List<GrantedAuthority> auth = AuthorityUtils .commaSeparatedStringToAuthorityList("ROLE_USER");
        return new org.springframework.security.core.userdetails.User(username, password, auth);
    }
}