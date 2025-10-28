package api.online.ecommerce.commons.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCredentials {
    private final String username;
    private final String password;
    private final String role;
}
