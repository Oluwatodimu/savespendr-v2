package com.savespendr.backend.merchantservice.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("springSecurityAuditorAwareImpl")
public class SpringSecurityAuditorAwareImpl implements AuditorAware<String> {

    @Override
    public @NonNull Optional<String> getCurrentAuditor() {
        Optional<String> loggedInUser = getLoggedInUser();
        return Optional.of(loggedInUser.orElse("system"));
    }

    private static Optional<String> getLoggedInUser() {
        String principal = extractPrincipal(SecurityContextHolder.getContext().getAuthentication());
        return Optional.ofNullable(principal);
    }

    private static String extractPrincipal(Authentication authentication) {
       if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
           Jwt jwt = jwtAuthenticationToken.getToken();
           String username = jwt.getClaim("sub");
           System.out.println("successful");
           return username;
       }
       return null;
    }
}
