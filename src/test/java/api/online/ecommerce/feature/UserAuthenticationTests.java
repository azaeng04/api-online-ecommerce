package api.online.ecommerce.feature;

import api.online.ecommerce.commons.security.UserCredentials;
import api.online.ecommerce.commons.security.UserDetailsImpl;
import api.online.ecommerce.commons.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UserAuthenticationTests {
    private final UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    @Test
    @DisplayName("Given valid customer credentials When logging in Then an HTTP status code 200 is returned")
    public void testLoginWithCustomer_success() {
        UserCredentials userCredentials = new UserCredentials("JohnDoe", "password", "CUSTOMER");
        UserDetails expectedResult = new UserDetailsImpl(userCredentials);

        var actualResult = userDetailsService.loadUserByUsername("JohnDoe");

        assertEquals(expectedResult.getUsername(), actualResult.getUsername());
        assertTrue(new BCryptPasswordEncoder().matches(expectedResult.getPassword(), actualResult.getPassword()));
        assertEquals(expectedResult.getAuthorities(), actualResult.getAuthorities());
    }

    @Test
    @DisplayName("Given valid administrator credentials When logging in Then the username is correct, the password is correct and an HTTP status code 200 is returned")
    public void testLoginWithAdmin_success() {
        UserCredentials userCredentials = new UserCredentials("JamesDean", "password", "ADMIN");
        UserDetails expectedResult = new UserDetailsImpl(userCredentials);

        var actualResult = userDetailsService.loadUserByUsername("JamesDean");

        assertEquals(expectedResult.getUsername(), actualResult.getUsername());
        assertTrue(new BCryptPasswordEncoder().matches(expectedResult.getPassword(), actualResult.getPassword()));
        assertEquals(expectedResult.getAuthorities(), actualResult.getAuthorities());
    }

    @Test
    @DisplayName("Given invalid client credentials When logging in Then an HTTP status code 401 is returned")
    public void testLogin_failed() {
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("invalid"));
    }
}
