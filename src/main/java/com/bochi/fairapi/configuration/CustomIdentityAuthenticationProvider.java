package com.bochi.fairapi.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomIdentityAuthenticationProvider implements AuthenticationProvider {

    // TODO:
    // In this function we need to connect with identity provider
    // and validate the user
    // we are hardcoding for a single user for demo purposes
    UserDetails isValidUser(String username, String password) {
        if (username.equalsIgnoreCase("user")
                && password.equals("password")) {
            return User
                    .withUsername(username)
                    .password(password)
                    .roles("USER_ROLE")
                    .build();
        }
        return null;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = isValidUser(username, password);

        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Incorrect user credentials !!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
