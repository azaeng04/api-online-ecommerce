package api.online.ecommerce.commons.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsServiceImplTest {

    UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

    @Test
    public void testUserLoadingByUsername_pass() {
        UserCredentials expectedUserDetails = new UserCredentials("JohnDoe", "password", "USER");
        var actualResult = userDetailsService.loadUserByUsername("JohnDoe");

        assertNotNull(actualResult);
        assertEquals(expectedUserDetails.getUsername(), actualResult.getUsername());
        assertTrue(new BCryptPasswordEncoder().matches(expectedUserDetails.getPassword(), actualResult.getPassword()));
    }

    @Test
    public void testUserLoadingByUsername_fail() {
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("invalid"));
    }
}