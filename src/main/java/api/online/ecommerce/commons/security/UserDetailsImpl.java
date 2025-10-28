package api.online.ecommerce.commons.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final UserCredentials userCredentials;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.userCredentials.getRole()));
    }

    @Override
    public String getPassword() {
        return this.userCredentials.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userCredentials.getUsername();
    }
}
