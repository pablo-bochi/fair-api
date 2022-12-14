package com.backend.fairapi.configuration;

import com.backend.fairapi.core.property.BasicCredentialsProperty;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final BasicCredentialsProperty basicCredentialsProperty;
    private static final String SECURED_PATTERN = "/fair/api/**";
    private static final String SWAGGER_PATTERN = "/swagger-ui/**";
    private static final String OPENAPI_PATTERN = "/v3/api-docs/**";

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername(basicCredentialsProperty.getDefaultUser())
                .password(passwordEncoder().encode(basicCredentialsProperty.getDefaultPassword()))
                .roles("USER_ROLE")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(SECURED_PATTERN, SWAGGER_PATTERN, OPENAPI_PATTERN).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
