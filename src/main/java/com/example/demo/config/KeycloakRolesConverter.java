package com.example.demo.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRolesConverter
        implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Map<String, Object> resourceAccess = jwt.getClaimAsMap("resource_access");

        if (resourceAccess == null) {
            return Collections.emptyList();
        }

        Object clientAccess = resourceAccess.get("user-management-api");

        if (!(clientAccess instanceof Map<?, ?>)) {
            return Collections.emptyList();
        }

        Object roles = ((Map<?, ?>) clientAccess).get("roles");

        if (!(roles instanceof Collection<?>)) {
            return Collections.emptyList();
        }

        return ((Collection<?>) roles).stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}