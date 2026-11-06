package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        JwtAuthenticationConverter jwtConverter =
                new JwtAuthenticationConverter();

        jwtConverter.setJwtGrantedAuthoritiesConverter(
                new KeycloakRolesConverter()
        );

        http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtConverter);

        return http.build();
    }
}