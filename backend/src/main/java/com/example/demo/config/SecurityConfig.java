package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for APIs
            .csrf(csrf -> csrf.disable())

            // Specify URL authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll() // Open endpoints
                .anyRequest().authenticated() // All others require authentication
            )

            // Disable the default login form
            .formLogin(form -> form.disable())

            // Disable HTTP Basic auth popup
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}