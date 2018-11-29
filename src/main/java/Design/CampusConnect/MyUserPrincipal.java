package Design.CampusConnect;

import Design.CampusConnect.entity.Role;
import Design.CampusConnect.entity.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class MyUserPrincipal implements UserDetails {
    private Student user;

    public MyUserPrincipal(Student student) {
        this.user = user;
    }
    //...

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        if (this.user == null) {
            return null;
        }
        return this.user.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        //return this.user.isAccountNonExpired();
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return this.user.isAccountNonLocked();
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return this.user.isCredentialsNonExpired();
        return false;
    }

    @Override
    public boolean isEnabled() {
        //return this.user.isEnabled();
        return false;
    }

    public Student getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user + "]";
    }
}