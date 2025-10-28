package api.online.ecommerce.commons.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    List<UserCredentials> userCredentials = new ArrayList<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null) {
            throw new UsernameNotFoundException("Username cannot be null");
        }

        if(!username.equals("JohnDoe") && !username.equals("JamesDean")) {
            throw new UsernameNotFoundException("'"+ username +"' does not exist");
        }

        UserCredentials userCredentials;
        if(username.equals("JohnDoe")) {
            userCredentials = new UserCredentials("JohnDoe", this.passwordEncoder().encode("password"), "CUSTOMER");
        } else {
            userCredentials = new UserCredentials("JamesDean", this.passwordEncoder().encode("password"), "ADMIN");
        }
        return new UserDetailsImpl(userCredentials);
    }

    private PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
