package ru.tpu.andrew.configs;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.tpu.andrew.services.UserService;

@AllArgsConstructor
@Component
public class AuthProvider implements AuthenticationProvider {
    private final UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = userService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Неправильный пароль");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
